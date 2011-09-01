package world;

import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;

public class LightingList {

//	PointLight lamp_light = new PointLight();
//	lamp_light.setColor(ColorRGBA.Yellow);
//	lamp_light.setRadius(4f);
//	lamp_light.setPosition(new Vector3f(lamp_geo.getLocalTranslation()));
//	A DirectionalLight has no position, only a direction. It is considered "infinitely" far away and sends out parallel beams of light. It can cast shadows. You typically use it to simulate sun light:
//	DirectionalLight sun = new DirectionalLight();
//	sun.setColor(ColorRGBA.White);
//	sun.setDirection(new Vector3f(-1,13, -1,13, 1,13).normalizeLocal());
//	An AmbientLight influences the brightness of the scene globally. It has no direction and no location, and does not cast any shadows.
//	AmbientLight al = new AmbientLight();
//	al.setColor(ColorRGBA.White.mult(1.3f));

	
	// pointlamps have a colour, position and radius
	// directional light have a direction and a colour
	// ambientLights have only a colour
	// spotlights have a direction and a position and a colour and more stuff to implement later 
//new LightingList(lightingList.getSelectedValue().toString(), lightingColourList.getSelectedValue(), numberOfLights.getValue());
	private String lightingType;
	private String name;
//	private Vector3f position;
//	private Vector3f direction;
//	private float radius;
	private String colour;
	private int quantity;
	
	public LightingList(String lightSourceType, String colour, int numberOfLights) {
		
		
		this.lightingType = lightSourceType;
		this.colour = colour;
		this.quantity = numberOfLights;
		this.name = lightingType + "_num_" + numberOfLights + "_col_" + colour;
		
		
	}

	public String getLightingType() {
		return lightingType;
	}

	public void setLightingType(String lightingType) {
		this.lightingType = lightingType;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	
	
	
	
}
