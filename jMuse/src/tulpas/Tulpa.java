package tulpas;

import java.util.Date;
import java.util.Random;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.texture.Texture;

public abstract class Tulpa extends SimpleApplication {
	
	protected Geometry geometry = new Geometry();
	protected Material material = new Material();
	protected Texture texture;
	protected Random rnd = new Random(this.returnDateTimeAsLong());
		
	protected Tulpa() {
		super();
	}
	
	// abstract methods
	// public abstract void generateVectorLines();

	
	// general translation methods
	// move by a vector
	public void translate(Vector3f vector3f) {
		geometry.move(vector3f);
	}

	// move to an absolute x,y,z position
	public void position(Vector3f vector3f) {
		geometry.setLocalTranslation(vector3f);
	}

	
	// Getter and Setter methods
	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public long returnDateTimeAsLong() {
		long exactNow = new Date().getTime();
		return exactNow;
	}


}