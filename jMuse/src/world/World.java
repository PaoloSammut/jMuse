package world;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mathPhysicsEngine.WorldPoint;

import mathPhysicsEngine.WorldPoint;
import entities.CubeEntity;
import entities.Entity;
import entities.PyramidEntity;
import entities.SphereEntity;
import entities.WorldAxisEntity;

public class World {

	private List<Entity> entities = new ArrayList<Entity>(); // lists the actual objects
	private List<ObjectList> objectList = new ArrayList<ObjectList>(); // Lists definitions objects from main console

	public World(List<ObjectList> objectList) {

		this.objectList = objectList;

		// set up randomizer
		Random rnd = new Random();
		// rnd.setSeed(returnDateTimeAsLong());
		rnd.setSeed(184);
		float[] rgbaArray = new float[4];

		entities.add(new WorldAxisEntity());

		// objectList contains everything which needs creating in the worlds
		// TODO - add camera's, lightsources and sphereEjaculators to object
		for (ObjectList eachObject : getObjectList()) {

			rgbaArray = calculateEntityColour(eachObject.getColour().toString());

			int ix = 0;

			for (ix = 0; ix < eachObject.getQuantity(); ix++) {

				Integer switchTest = calculateEntityType(eachObject.getEntity().toString());
				rgbaArray = calculateEntityColour(eachObject.getColour().toString());

				switch (switchTest) {
				case 1:
					CubeEntity cube = new CubeEntity(rnd.nextGaussian() * 2, new WorldPoint((double) rnd.nextGaussian() * 100,
							(double) rnd.nextGaussian() * 100, (double) rnd.nextGaussian() * 100));
					cube.setRgbaColour(rgbaArray);
					cube.translateVector(new WorldPoint((double) rnd.nextGaussian() * 100, (double) rnd.nextGaussian() * 100, (double) rnd
							.nextGaussian() * 100));
					entities.add(cube);
					break;
				case 2:
					PyramidEntity pyramid = new PyramidEntity(rnd.nextGaussian() * 2);
					pyramid.translateVector(new WorldPoint((double) rnd.nextGaussian() * 100, (double) rnd.nextGaussian() * 100, (double) rnd
							.nextGaussian() * 100));
					pyramid.setRgbaColour(rgbaArray);
					entities.add(pyramid);

					break;
				case 3:
					double x = rnd.nextGaussian() * 100;
					double y = rnd.nextGaussian() * 100;
					double z = rnd.nextGaussian() * 100;

					// Here we can create out first sphere
					SphereEntity sphere = new SphereEntity();

					sphere.translateVector(new WorldPoint(x, y, z));
					sphere.setRgbaColour(rgbaArray);
					entities.add(sphere);

					break;
				default:
					System.out.println("Mystery detected");
					break;

				}

			}

		}

	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<ObjectList> getObjectList() {
		return objectList;
	}

	public int calculateEntityType(String entityString) {

		Map<String, Integer> entityMap = new HashMap<String, Integer>();
		entityMap.put("Cube", 1);
		entityMap.put("Pyramid", 2);
		entityMap.put("Sphere", 3);
		entityMap.put("SphereGun", 4);

		return entityMap.get(entityString);

	}

	public float[] calculateEntityColour(String colour) {

		float[] rgbaArray = new float[4];

		rgbaArray[0] = 0; // RED
		rgbaArray[1] = 0; // GREEN
		rgbaArray[2] = 0; // BLUE
		rgbaArray[3] = 0; // ALPHA

		// set up randomizer

		Random rnd = new Random();

		rnd.setSeed(returnDateTimeAsLong()); // TODO find a way to ties this to
												// date/time so that it is
												// different each time
		// double value = rnd.nextGaussian();

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
			rgbaArray[0] = (float) rnd.nextDouble(); // RED
			rgbaArray[1] = (float) rnd.nextDouble(); // GREEN
			rgbaArray[2] = (float) rnd.nextDouble(); // BLUE
			// rgbaArray[3] = (float) rnd.nextDouble(); // ALPHA
			rgbaArray[3] = 1F; // ALPHA
			break;
		case 2: // white
			rgbaArray[0] = 1; // RED
			rgbaArray[1] = 1; // GREEN
			rgbaArray[2] = 1; // BLUE
			rgbaArray[3] = 1; // ALPHA
			// White
			break;
		case 3: // Yellow
			rgbaArray[0] = 1; // RED
			rgbaArray[1] = 1; // GREEN
			rgbaArray[2] = 0; // BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 4: // Red
			rgbaArray[0] = 1; // RED
			rgbaArray[1] = 0; // GREEN
			rgbaArray[2] = 0; // BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 5: // Blue
			rgbaArray[0] = 0; // RED
			rgbaArray[1] = 0; // GREEN
			rgbaArray[2] = 1; // BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 6: // Green
			rgbaArray[0] = 0; // RED
			rgbaArray[1] = 1; // GREEN
			rgbaArray[2] = 0; // BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 7: // Cyan
			rgbaArray[0] = 0.5F; // RED
			rgbaArray[1] = 0.5F;// GREEN
			rgbaArray[2] = 0.7F;// BLUE
			rgbaArray[3] = 1; // ALPHA

			break;
		case 8: // Gray
			rgbaArray[0] = 0.7F; // RED
			rgbaArray[1] = 0.7F;// GREEN
			rgbaArray[2] = 0.7F;// BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 9: // Orange
			rgbaArray[0] = 0.7F; // RED
			rgbaArray[1] = 0.5F;// GREEN
			rgbaArray[2] = 0.3F;// BLUE
			rgbaArray[3] = 1F; // ALPHA
			break;
		case 10: // Magenta
			rgbaArray[0] = 1F; // RED
			rgbaArray[1] = 0F;// GREEN
			rgbaArray[2] = 1F;// BLUE
			rgbaArray[3] = 1F; // ALPHA

			break;
		case 11: // Pink
			rgbaArray[0] = 1; // RED
			rgbaArray[1] = 0.5F;// GREEN
			rgbaArray[2] = 0.5F;// BLUE
			rgbaArray[3] = 1; // ALPHA
			break;
		case 12: // Light Gray
			rgbaArray[0] = 0.7F; // RED
			rgbaArray[1] = 0.7F;// GREEN
			rgbaArray[2] = 0.7F;// BLUE
			rgbaArray[3] = 1F; // ALPHA
			break;
		case 13: // Dark Gray
			rgbaArray[0] = 0.7F; // RED
			rgbaArray[1] = 0.7F;// GREEN
			rgbaArray[2] = 0.7F;// BLUE
			rgbaArray[3] = 0.3F; // ALPHA
			break;
		case 14: // Black
			rgbaArray[0] = 0F; // RED
			rgbaArray[1] = 0F;// GREEN
			rgbaArray[2] = 0F;// BLUE
			rgbaArray[3] = 0; // ALPHA

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

}
