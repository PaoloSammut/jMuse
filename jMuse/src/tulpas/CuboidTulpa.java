package tulpas;

import java.util.Random;

import jMuse.JMonkeyCanvas;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.JmeSystem;

public class CuboidTulpa extends Tulpa {
	
	private String wallPaper;
	private Random rnd = new Random(this.returnDateTimeAsLong());
	
		public CuboidTulpa(AssetManager assetManager, float xp, float yp, float zp, float xs, float ys, float zs, ColorRGBA rgbaArray, int colourModel, Boolean wireframe, String wallpaper) {
			this.wallPaper = wallpaper;
			// 0 = simple texture
			// 1 = wallpapered
			// 2 = lighting model
			
			//int colourModel = 1;
			
			Box b = new Box(new Vector3f(xp, yp, zp), xs, ys, zs);
			geometry = new Geometry("Cube", b);
			
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
			// will probably need to change this if we want to create objects in alignment
			// this rotates the cube randomly about 3 Axi  - only bother rotating upto 90 degrees, 
			geometry.rotate((float) rnd.nextGaussian() * 90, (float) rnd.nextGaussian() * 90, (float) rnd.nextGaussian() * 90);
	}

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
	}
}
