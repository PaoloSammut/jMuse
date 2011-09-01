package tulpas;

import jMuse.JMonkeyCanvas;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.JmeSystem;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.TangentBinormalGenerator;

public class SphereTulpa extends Tulpa {
	private String name = "Sphere";
	private int zSamples;
	private int radialSamples;
	private float radius;
	private Sphere mesh = new Sphere(16, 32, 5f);
	private String wallPaper;

	public SphereTulpa(AssetManager assetManager, int zS, int rS, float rad, ColorRGBA rgbaArray, int colourModel, Boolean wireframe, String wallpaper) {
		this.zSamples = zS;
		this.radialSamples = rS;
		this.radius = 1;
		this.wallPaper = wallpaper;

		geometry = new Geometry("Sphere", mesh);
		mesh.setTextureMode(Sphere.TextureMode.Projected);

		switch (colourModel) {
		case 1: // simple texture
			material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			material.setColor("Color", rgbaArray);
			material.getAdditionalRenderState().setWireframe(wireframe);
			break;
		case 2: // wallpapered
			material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			texture = assetManager.loadTexture("Interface/Logo/" + wallPaper);
			material.setTexture("ColorMap", texture);
			break;
		case 3: // lighting model
			material = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
			material.setFloat("Shininess", 12);
			material.setBoolean("UseMaterialColors", false);
			break;
		case 4: // reflective
			break;
		default:
			break;
		}
		geometry.setMaterial(material);
		
//		// temp
//		RigidBodyControl ball_phy;
//		ball_phy = new RigidBodyControl(1f);
//		geometry.addControl(ball_phy);
//		//BulletAppState bulletAppState = new BulletAppState();
//		//stateManager.attach(bulletAppState);
//	
//		bulletAppState.getPhysicsSpace().add(ball_phy);
//		/** Accelerate the physcial ball to shoot it. */
//		ball_phy.setLinearVelocity(cam.getDirection().mult(5));
		
		
		
	}

	public int getzSamples() {
		return zSamples;
	}

	public void setzSamples(int zSamples) {
		this.zSamples = zSamples;
	}

	public int getRadialSamples() {
		return radialSamples;
	}

	public void setRadialSamples(int radialSamples) {
		this.radialSamples = radialSamples;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

}
