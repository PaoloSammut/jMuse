package world;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

public class LightingEngine {

	private List<LightingList> lightingList; // list of what to create
	private List<Light> lightObjectList = new ArrayList<Light>(); // list of what is created

	public LightingEngine(List<LightingList> lightList, AssetManager assetManager) {
		this.lightingList = lightList;

		System.out.println("in lighting engine + --");

		Random rnd = new Random();
		rnd.setSeed(returnDateTimeAsLong());

		for (LightingList eachLight : lightList) {
			int ix = 0;

			for (ix = 0; ix < eachLight.getQuantity(); ix++) {

				Integer lightTest = calculateLightType(eachLight.getLightingType().toString());

				switch (lightTest) {

				case 1: // Pointlight needs colour, radius and position
					PointLight p1 = new PointLight();
					p1.setRadius((float) rnd.nextDouble() * 10);
					p1.setPosition(new Vector3f((float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100));
					p1.setColor(calculateLightColour(eachLight.getColour()));
					lightObjectList.add(p1);
					break;
				case 2: // directionlight needs colour and direction
					DirectionalLight d1 = new DirectionalLight();
					d1.setDirection(new Vector3f((float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100));
					d1.setColor(calculateLightColour(eachLight.getColour()));
					lightObjectList.add(d1);
					break;
				case 3: // ambientlight needs colour
					AmbientLight a1 = new AmbientLight();
					a1.setColor(calculateLightColour(eachLight.getColour()));
					lightObjectList.add(a1);
					break;
				case 4: // spotlight needs position, direction and colour
					SpotLight s1 = new SpotLight();
					s1.setPosition(new Vector3f((float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100));
					s1.setDirection(new Vector3f((float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100, (float) rnd.nextGaussian() * 100));
					s1.setColor(calculateLightColour(eachLight.getColour()));
					lightObjectList.add(s1);
					break;
				default:
					System.out.println("Unknown Light type detected");
					break;

				} // end of switch

			} // end of for loop

		}

	}

	public int calculateLightType(String entityString) {

		Map<String, Integer> entityMap = new HashMap<String, Integer>();
		entityMap.put("POINTLIGHT", 1);
		entityMap.put("DIRECTIONLIGHT", 2);
		entityMap.put("AMBIENTLIGHT", 3);
		entityMap.put("SPOTLIGHT", 4);
		return entityMap.get(entityString);

	}

	public ColorRGBA calculateLightColour(String colour) {

		ColorRGBA rgbaArray = new ColorRGBA();

		rgbaArray.set(0, 0, 0, 0); // initialise

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
			rgbaArray.set((float)rnd.nextDouble(), (float)rnd.nextDouble(), (float)rnd.nextDouble(), (float)1);
			break;
		case 2: // white
			rgbaArray.set((float) 1, (float)1, (float)1, (float)1);
			break;
		case 3: // Yellow
			rgbaArray.set((float) 1, (float)1, (float)0, (float)1);
			break;
		case 4: // Red
			rgbaArray.set((float) 1, (float)0, (float)0, (float)1);
			break;
		case 5: // Blue
			rgbaArray.set((float) 0, (float)0, (float)1, (float)1);
			break;
		case 6: // Green
			rgbaArray.set((float) 0, (float)1, (float)0, (float)1);
			break;
		case 7: // Cyan
			rgbaArray.set((float)0, (float)1, (float)1, (float)1);
			break;
		case 8: // Gray
			rgbaArray.set((float) 0.7, (float)0.7, (float)0.7, (float)1);
			break;
		case 9: // Orange
			rgbaArray.set((float) 1, (float)0.5, (float)0, (float)1);
			break;
		case 10: // Magenta
			rgbaArray.set((float) 1, (float)0, (float)1, (float)1);
			break;
		case 11: // Pink   255, 192, 203
			rgbaArray.set((float) 1, (float)0.7529, (float)0.7960, (float)1);
			break;
		case 12: // Light Gray
			rgbaArray.set((float) 0.7, (float)0.7, (float)0.7, (float)1);
			break;
		case 13: // Dark Gray
			rgbaArray.set((float) 0.3, (float)0.3, (float)0.3, (float)1);
			break;
		case 14: // Black
			rgbaArray.set((float) 0, (float)0, (float)0, (float)0);
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

	public List<LightingList> getLightingList() {
		return lightingList;
	}

	public void setLightingList(List<LightingList> lightingList) {
		this.lightingList = lightingList;
	}

	public List<Light> getLightObjectList() {
		return lightObjectList;
	}

	public void setLightObjectList(List<Light> lightObjectList) {
		this.lightObjectList = lightObjectList;
	}
}
