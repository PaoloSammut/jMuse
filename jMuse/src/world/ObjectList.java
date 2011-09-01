package world;


public class ObjectList {
	// basic object attributes
	private String name;
	private String entity;
	private int quantity;
	
	// Single colour attributes
	private String colour;
	private float transparency;
	private boolean wireframe;
	private String wallPaper;
	private int colourFlag;

	// Wall paper attributes
	
	public ObjectList(String entity, int quantity, int colourFlag) {
		this.entity = entity;
		this.quantity = quantity;
		this.colourFlag = colourFlag;
		this.name = entity + "_num_" + quantity + "_cf_" + colourFlag;
	}
	
	public String getEntity() {
		return entity;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getColour() {
		return colour;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public float getTransparency() {
		return transparency;
	}

	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public boolean isWireframe() {
		return wireframe;
	}

	public void setWireframe(boolean wireframe) {
		this.wireframe = wireframe;
	}

	public String getWallPaper() {
		return wallPaper;
	}

	public void setWallPaper(String wallPaper) {
		this.wallPaper = wallPaper;
	}

	public int getColourFlag() {
		return colourFlag;
	}

	public void setColourFlag(int colourFlag) {
		this.colourFlag = colourFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
