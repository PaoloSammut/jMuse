package jMuse;

import java.util.ArrayList;
import java.util.List;

import mathPhysicsEngine.Camera;
import world.LightingEngine;
import world.LightingList;
import world.ObjectList;
import world.WorldEngine;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.Light;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import jMuse.JMonkeyCanvas;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;


public class JMonkeyCanvas extends SimpleApplication {

	// private Geometry player;
	public static boolean ROTATE = false;
	// private static int frameWidth = 1300;
	// private static int worldHeight = 700;
	private List<Node> nodeList = new ArrayList<Node>();

	private List<ObjectList> objectList = JMuseViewer.objectList;
	private List<LightingList> lightList = new ArrayList<LightingList>();
	private static Camera camera = JMuseViewer.camera;
	// private boolean worldCreator = false;
	// private World world;

	public static JMonkeyCanvas app;

	public static void main(String[] args) {

		app = new JMonkeyCanvas();
		app.start();

	}

	@Override
	public void simpleInitApp() {
		flyCam.setDragToRotate(true);
		
		// initialise physics
//		BulletAppState bulletAppState = new BulletAppState();
//		stateManager.attach(bulletAppState);

		// set the camera
		cam.setLocation(camera.getCameraPosition());
		cam.lookAt(camera.getCameraLookAt(), new Vector3f(0, 1, 0));

		// bring in ones objects
		this.objectList = JMuseViewer.objectList;
		this.lightList = JMuseViewer.lightList;

		// Draw a sphere natively in this page
		Sphere mesh = new Sphere(16, 32, 1.5f);
		Geometry geom = new Geometry("Sphere", mesh);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Green);
		geom.setMaterial(mat);
		mat.getAdditionalRenderState().setWireframe(true);
		rootNode.attachChild(geom);

		jme3tools.optimize.GeometryBatchFactory.optimize(rootNode);

		WorldEngine worldEngine = new WorldEngine(objectList, assetManager);
		nodeList = worldEngine.getNodes();

		for (Node n : nodeList) {
			rootNode.attachChild(n);
		}

		List<Light> lightObjectList = new ArrayList<Light>();
		LightingEngine lightEngine = new LightingEngine(lightList, assetManager);
		lightObjectList = lightEngine.getLightObjectList();

		for (Light l : lightObjectList) {
			rootNode.addLight(l);
		}


	}

	/* This is the update loop */
	@Override
	public void simpleUpdate(float tpf) {
		// make the player rotate
		// if (ROTATE) {
		// rootNode.rotate(0, (float) (0.1 * tpf), 0);
		// player.rotate(0, 2 * tpf, 0);
		// }

		for (Node n : nodeList) {

			String lname = n.getName();
			// n.rotate(0, 0.1F * tpf, 0);

			if (lname.equals("cube0")) {
				n.rotate(0, -0.7F * tpf, 0);
			} else if (lname.equals("cube1")) {
				n.rotate(0, 0.4F * tpf, 0);
			} else if (lname.equals("sphere0")) {
				n.rotate(-0.3F * tpf, 0, 0);
			}
			if (lname.equals("sphere1")) {
				n.rotate(0, 0, 1.1F * tpf);
			} else {
				n.rotate(-0.3F * tpf, 0, 0);

			}

		}

	}

	public static JMonkeyCanvas getApp() {
		return app;
	}

}