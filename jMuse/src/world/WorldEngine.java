package world;

import jMuse.JMuseViewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import entities.CubeEntity;
import entities.PyramidEntity;
import entities.SphereEntity;
import entities.WorldAxisEntity;
import mathPhysicsEngine.WorldPoint;
import tulpas.CubeTulpa;
import tulpas.CuboidTulpa;
import tulpas.SphereTulpa;
import world.ObjectList;

public class WorldEngine {
	private Integer nodeId = 0;
	private String name = "";
	private List<ObjectList> objectList;
	//private List<Geometry> geometryList = new ArrayList<Geometry>();
	private List<Node> nodeList = new ArrayList<Node>();

	public WorldEngine(List<ObjectList> objectList, AssetManager assetManager) {
		this.objectList = objectList;

		Random rnd = new Random();
		rnd.setSeed(returnDateTimeAsLong());
		//rnd.setSeed(184);
		
		ColorRGBA rgbaArray = new ColorRGBA();
		float xp;
		float yp;
		float zp;

		float xs;
		float ys;
		float zs;

		// entities.add(new WorldAxisEntity());

		// process objects starting with spheres.
		for (ObjectList eachObject : objectList) {
			int ix = 0;

			List<Geometry> geometryList = new ArrayList<Geometry>();
			
			for (ix = 0; ix < eachObject.getQuantity(); ix++) {

				Integer switchTest = calculateEntityType(eachObject.getEntity().toString());
				rgbaArray = calculateEntityColour(eachObject.getColour().toString(), eachObject.getTransparency());

				switch (switchTest) {
				case 1:
					xp = (float) rnd.nextGaussian() * 100;
					yp = (float) rnd.nextGaussian() * 100;
					zp = (float) rnd.nextGaussian() * 100;
					xs = (float) rnd.nextGaussian() * 10;
					CubeTulpa cube1 = new CubeTulpa(assetManager, xp, yp, zp, xs, rgbaArray, eachObject.getColourFlag(), eachObject.isWireframe(), eachObject.getWallPaper());
					geometryList.add(cube1.getGeometry());
					name = "cube";
					break;
				case 2:
					// PyramidEntity pyramid = new PyramidEntity(rnd.nextGaussian()*2);
					// pyramid.translateVector(new WorldPoint((double)rnd.nextGaussian() * 100,(double)rnd.nextGaussian() *
					// 100,(double)rnd.nextGaussian() * 100));
					// pyramid.setRgbaColour(rgbaArray);
					// entities.add(pyramid);
					name = "pyramid";
					break;
				case 3:
					float x = (float) rnd.nextGaussian() * 100;
					float y = (float) rnd.nextGaussian() * 100;
					float z = (float) rnd.nextGaussian() * 100;
					float s = (float) rnd.nextGaussian() * 100;
					System.out.println("worldEngine:  " + eachObject.getColourFlag() + eachObject.getWallPaper());
					SphereTulpa sphere1 = new SphereTulpa(assetManager, 32, 51, s, rgbaArray, eachObject.getColourFlag(), eachObject.isWireframe(), eachObject.getWallPaper());
					sphere1.position(new Vector3f(x, y, z));
					geometryList.add(sphere1.getGeometry());
					name = "sphere";
					break;
				case 4:
					xp = (float) rnd.nextGaussian() * 100;
					yp = (float) rnd.nextGaussian() * 100;
					zp = (float) rnd.nextGaussian() * 100;
					xs = (float) rnd.nextGaussian() * 10;
					ys = (float) rnd.nextGaussian() * 10;
					zs = (float) rnd.nextGaussian() * 10;
					CuboidTulpa cuboid1 = new CuboidTulpa(assetManager, xp, yp, zp, xs, ys, zs, rgbaArray, eachObject.getColourFlag(), eachObject.isWireframe(), eachObject.getWallPaper());
					geometryList.add(cuboid1.getGeometry());
					name = "cuboid";
					break;
				default:
					System.out.println("Mystery detected");
					break;

				}  // end of switch

			}  // end of for (ix = 0; ix < eachObject.getQuantity(); ix++) {

			// Now add all the entries in tempGeometryList to a new Node
			Node newNode = new Node(name +  nodeId.toString());
			nodeId++;
			for (Geometry eachGeometry: geometryList) {
				newNode.attachChild(eachGeometry);
			} // end of node mapping for look
			nodeList.add(newNode);
			
		}  // end of for (ObjectList eachObject : objectList) {

	} // end of WorldEngine(List<ObjectList> objectList, AssetManager assetManager) {

	public int calculateEntityType(String entityString) {

		Map<String, Integer> entityMap = new HashMap<String, Integer>();
		entityMap.put("Cube", 1);
		entityMap.put("Pyramid", 2);
		entityMap.put("Sphere", 3);
		entityMap.put("Cuboid", 4);

		return entityMap.get(entityString);

	}

	public ColorRGBA calculateEntityColour(String colour, float transparency) {

		ColorRGBA rgbaArray = new ColorRGBA();

//		rgbaArray.set(0, 0, 0, 0);

		// set up randomizer
		Random rnd = new Random();
		rnd.setSeed(returnDateTimeAsLong());
		
		Map<String, Integer> colourMap = new HashMap<String, Integer>();
		colourMap.put("RANDOM", 1);
		colourMap.put("WHITE", 2);
		colourMap.put("YELLOW", 3);
		colourMap.put("RED", 4);
		colourMap.put("BLUE", 5);
		colourMap.put("GREEN", 6);
		colourMap.put("CYAN", 7);
		colourMap.put("GRAY", 8);
		colourMap.put("ORANGE", 9);
		colourMap.put("MAGENTA", 10);
		colourMap.put("PINK", 11);
		colourMap.put("LIGHT_GRAY", 12);
		colourMap.put("DARK_GRAY", 13);
		colourMap.put("BLACK", 14);

		int switchTest = (int) colourMap.get(colour);

		switch (switchTest) {
		case 1:
			// Random;
			rgbaArray.set((float)rnd.nextDouble(), (float)rnd.nextDouble(), (float)rnd.nextDouble(), transparency);
			break;
		case 2: // white
			rgbaArray.set((float) 1, (float)1, (float)1, transparency);
			break;
		case 3: // Yellow
			rgbaArray.set((float) 1, (float)1, (float)0, transparency);
			break;
		case 4: // Red
			rgbaArray.set((float) 1, (float)0, (float)0, transparency);
			break;
		case 5: // Blue
			rgbaArray.set((float) 0, (float)0, (float)1, transparency);
			break;
		case 6: // Green
			rgbaArray.set((float) 0, (float)1, (float)0, transparency);
			break;
		case 7: // Cyan
			rgbaArray.set((float)0, (float)1, (float)1, transparency);
			break;
		case 8: // Gray
			rgbaArray.set((float) 0.7, (float)0.7, (float)0.7, transparency);
			break;
		case 9: // Orange
			rgbaArray.set((float) 1, (float)0.5, (float)0, transparency);
			break;
		case 10: // Magenta
			rgbaArray.set((float) 1, (float)0, (float)1, transparency);
			break;
		case 11: // Pink   255, 192, 203
			rgbaArray.set((float) 1, (float)0.7529, (float)0.7960, transparency);
			break;
		case 12: // Light Gray
			rgbaArray.set((float) 0.7, (float)0.7, (float)0.7, transparency);
			break;
		case 13: // Dark Gray
			rgbaArray.set((float) 0.3, (float)0.3, (float)0.3, transparency);
			break;
		case 14: // Black
			rgbaArray.set((float) 0, (float)0, (float)0, transparency);
			break;
		default:
			System.out.println("Mystery detected");
			break;
		}

		return rgbaArray;
	}

	public long returnDateTimeAsLong() {
		long exactNow = new Date().getTime();
		return exactNow;
	}

//	public List<Geometry> getGeometries() {
//		return geometryList;
//	}

	public List<Node> getNodes() {
		return nodeList;
	}


}
