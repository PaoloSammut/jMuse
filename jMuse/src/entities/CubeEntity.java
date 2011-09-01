package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import mathPhysicsEngine.Line;
import mathPhysicsEngine.ScreenPoint;
import mathPhysicsEngine.WorldPoint;
import world.ObjectList;




public class CubeEntity extends Entity {

	// your basic no frills cube at the centre of the world (in 3D space)
	public CubeEntity() {
		noOfVertices = 8;
		noOfLines = 12;
		objectType = "CUBE";

		objectPoints = new WorldPoint[noOfVertices];
		screenPoints = new ScreenPoint[noOfVertices];
		objectWorldLines = new Line[noOfLines];
		objectScreenLines = new Line[noOfLines];
		objectPolygons = new Polygon[6];

		objectPoints[0] = new WorldPoint((double) -10, (double) 10, (double) 10);
		objectPoints[1] = new WorldPoint((double) 10, (double) 10, (double) 10);
		objectPoints[2] = new WorldPoint((double) 10, (double) -10, (double) 10);
		objectPoints[3] = new WorldPoint((double) -10, (double) -10, (double) 10);
		objectPoints[4] = new WorldPoint((double) -10, (double) 10, (double) -10);
		objectPoints[5] = new WorldPoint((double) 10, (double) 10, (double) -10);
		objectPoints[6] = new WorldPoint((double) 10, (double) -10, (double) -10);
		objectPoints[7] = new WorldPoint((double) -10, (double) -10, (double) -10);

		regenerateObject();
	}
	
	public CubeEntity(double scaleFactor) {
		this();
		this.scaleEntity(scaleFactor);
	}
	
	public CubeEntity(double scaleFactor, WorldPoint translation) {
		this();
		this.scaleEntity(scaleFactor);
		this.translateVector(translation);
	}


	@Override
	public void generateVectorLines() {
		objectWorldLines[0] = new Line(objectPoints[0].getWorldPoint(),objectPoints[1].getWorldPoint());
		objectWorldLines[1] = new Line(objectPoints[1].getWorldPoint(),objectPoints[2].getWorldPoint());
		objectWorldLines[2] = new Line(objectPoints[2].getWorldPoint(),objectPoints[3].getWorldPoint());
		objectWorldLines[3] = new Line(objectPoints[3].getWorldPoint(),objectPoints[0].getWorldPoint());
		objectWorldLines[4] = new Line(objectPoints[4].getWorldPoint(),objectPoints[5].getWorldPoint());
		objectWorldLines[5] = new Line(objectPoints[5].getWorldPoint(),objectPoints[6].getWorldPoint());
		objectWorldLines[6] = new Line(objectPoints[6].getWorldPoint(),objectPoints[7].getWorldPoint());
		objectWorldLines[7] = new Line(objectPoints[7].getWorldPoint(),objectPoints[4].getWorldPoint());
		objectWorldLines[8] = new Line(objectPoints[0].getWorldPoint(),objectPoints[6].getWorldPoint());
		objectWorldLines[9] = new Line(objectPoints[1].getWorldPoint(),objectPoints[7].getWorldPoint());
		objectWorldLines[10] = new Line(objectPoints[2].getWorldPoint(),objectPoints[4].getWorldPoint());
		objectWorldLines[11] = new Line(objectPoints[3].getWorldPoint(),objectPoints[5].getWorldPoint());
	}

//	public void generatePolygons() {
//		
//		// use swing polygon, takes array of x, array of y 
//		
//		objectPolygons[0] = new Polygon
//g.	
//	
//	}
	
	@Override
	public void generateLineScreenCoordinates() {
		objectScreenLines[0] = new Line(screenPoints[0], screenPoints[1]);
		objectScreenLines[1] = new Line(screenPoints[1], screenPoints[2]);
		objectScreenLines[2] = new Line(screenPoints[2], screenPoints[3]);
		objectScreenLines[3] = new Line(screenPoints[3], screenPoints[0]);
		objectScreenLines[4] = new Line(screenPoints[4], screenPoints[5]);
		objectScreenLines[5] = new Line(screenPoints[5], screenPoints[6]);
		objectScreenLines[6] = new Line(screenPoints[6], screenPoints[7]);
		objectScreenLines[7] = new Line(screenPoints[7], screenPoints[4]);
		objectScreenLines[8] = new Line(screenPoints[0], screenPoints[4]);
		objectScreenLines[9] = new Line(screenPoints[1], screenPoints[5]);
		objectScreenLines[10] = new Line(screenPoints[2], screenPoints[6]);
		objectScreenLines[11] = new Line(screenPoints[3], screenPoints[7]);
	}


	
	
	// returns a list of lines which the calling function (on panel) will then paint 
	public void generatePaintLines(int frameCentreX, int frameCentreY) {
		
		

		
	}

	@Override
	public void paintEntity(Graphics g, int frameCentreX, int frameCentreY) {

		Color colour = new Color((float) rgbaColour[0], (float) rgbaColour[1],	(float) rgbaColour[2], (float) rgbaColour[3]);
		
		for(Line lines : objectScreenLines) {
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(colour);

			g2d.drawLine((int) lines.getStartScreenCoordinates().getX() + frameCentreX, 
					     (int) lines.getStartScreenCoordinates().getY() + frameCentreY, 
					     (int) lines.getEndScreenCoordinates().getX() + frameCentreX,
					     (int) lines.getEndScreenCoordinates().getY() + frameCentreY);
		}
		
	}

	

		
}
