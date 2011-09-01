package entities;

import java.awt.Graphics;
import java.awt.Polygon;

import mathPhysicsEngine.FourPointMatrix;
import mathPhysicsEngine.Line;
import mathPhysicsEngine.ScreenPoint;
import mathPhysicsEngine.WorldPoint;

public abstract class Entity {
	protected int noOfVertices;
	protected int noOfLines;
	protected String objectType;

	protected WorldPoint[] objectPoints;
	protected ScreenPoint[] screenPoints;
	protected Line[] objectWorldLines;
	protected Polygon[] objectPolygons;
	protected Polygon[] screenPolygons;

	protected Line[] objectScreenLines;

	protected float[] rgbaColour = new float[4];

	public Entity() {

	}

	// abstract methods
	public abstract void generateVectorLines();

	public abstract void generateLineScreenCoordinates();

	public abstract void paintEntity(Graphics g, int frameCentreX,
			int frameCentreY);

	public void generateScreenCoordinates() {
		int i = 0;
		for (i = 0; i < noOfVertices; i++) {

			screenPoints[i] = objectPoints[i].perspective(objectPoints[i]);
		}
	}

	public void regenerateObject() {
		generateScreenCoordinates();
		generateVectorLines();
		generateLineScreenCoordinates();
	}

	public void scaleEntity(double scaleFactor) {
		for (WorldPoint vector : objectPoints) {
			vector.scaleVector(scaleFactor);
		}
	}

	public void translateVector(WorldPoint translation) {
		for (WorldPoint vector : objectPoints) {
			vector.translateVector(translation);
		}
	}

	public Line[] getObjectScreenLines() {
		return objectScreenLines;
	}

	public void setObjectScreenLines(Line[] objectScreenLines) {
		this.objectScreenLines = objectScreenLines;
	}

	public float[] getRgbaColour() {
		return rgbaColour;
	}

	public void setRgbaColour(float[] rgbaArray) {
		this.rgbaColour = rgbaArray;
	}

	public String getObjectType() {
		return objectType;
	}

	public void rotateXAxis(double d) {

		if (this.getObjectType() != "AXIS") {

			double cosTheta = Math.cos(Math.toRadians(d));
			double sinTheta = Math.sin(Math.toRadians(d));
			double minusSinTheta = sinTheta * -1;

			FourPointMatrix rotateX = new FourPointMatrix(1D, 0, 0, 0, 0,
					cosTheta, sinTheta, 0, 0, minusSinTheta, cosTheta, 0, 0, 0,
					0, 1D);

			for (WorldPoint point : objectPoints) {
				point.transform(rotateX);
				regenerateObject();
			}
		}
	}

	public void rotateYAxis(double d) {

		if (this.getObjectType() != "AXIS") {

			double cosTheta = Math.cos(Math.toRadians(d));
			double sinTheta = Math.sin(Math.toRadians(d));
			double minusSinTheta = sinTheta * -1;

			FourPointMatrix rotateY = new FourPointMatrix(cosTheta, 0, minusSinTheta, 0,	0,	1D,	0,	0,	sinTheta, 0, cosTheta, 0, 0, 0,	0, 1D);

			for (WorldPoint point : objectPoints) {
				point.transform(rotateY);
				regenerateObject();
			}
		}
	}
	
	public void rotateZAxis(double d) {

		if (this.getObjectType() != "AXIS") {

			
//			double cosTheta = FastMath.cos((float) (FastMath.DEG_TO_RAD * d));
//			double sinTheta = FastMath.sin((float) (FastMath.DEG_TO_RAD * d));
			
			double cosTheta = Math.cos(Math.toRadians(d));
			double sinTheta = Math.sin(Math.toRadians(d));
			double minusSinTheta = sinTheta * -1;

			FourPointMatrix rotateZ = new FourPointMatrix(cosTheta, sinTheta, 0, 0,	minusSinTheta, cosTheta, 0, 0, 0, 0, 1D, 0, 0, 0, 0, 1D);

			for (WorldPoint point : objectPoints) {
				point.transform(rotateZ);
				regenerateObject();
			}
		}
	}
	
	
	
}
