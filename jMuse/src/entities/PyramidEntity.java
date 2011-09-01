package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mathPhysicsEngine.Line;
import mathPhysicsEngine.ScreenPoint;
import mathPhysicsEngine.WorldPoint;



public class PyramidEntity extends Entity {

	public PyramidEntity() {
		noOfVertices = 5;
		noOfLines = 8;
		objectType = "PYRAMID";

		objectPoints = new WorldPoint[noOfVertices];
		screenPoints = new ScreenPoint[noOfVertices];
		objectWorldLines = new Line[noOfLines];
		objectScreenLines = new Line[noOfLines];

		objectPoints[0] = new WorldPoint((double) -10, (double) 10, (double) -10);
		objectPoints[1] = new WorldPoint((double) 10, (double) 10, (double) -10);
		objectPoints[2] = new WorldPoint((double) 10, (double) -10, (double) -10);
		objectPoints[3] = new WorldPoint((double) -10, (double) -10, (double) -10);
		objectPoints[4] = new WorldPoint((double) 0, (double) 0, (double) 10);

		regenerateObject();
	}

	public PyramidEntity(double scaleFactor) {
		this();
		this.scaleEntity(scaleFactor);
	}
	
	
	
	@Override
	public void generateVectorLines() {
		objectWorldLines[0] = new Line(objectPoints[0].getWorldPoint(),objectPoints[1].getWorldPoint());
		objectWorldLines[1] = new Line(objectPoints[1].getWorldPoint(),objectPoints[2].getWorldPoint());
		objectWorldLines[2] = new Line(objectPoints[2].getWorldPoint(),objectPoints[3].getWorldPoint());
		objectWorldLines[3] = new Line(objectPoints[3].getWorldPoint(),objectPoints[0].getWorldPoint());
		objectWorldLines[4] = new Line(objectPoints[0].getWorldPoint(),objectPoints[4].getWorldPoint());
		objectWorldLines[5] = new Line(objectPoints[1].getWorldPoint(),objectPoints[4].getWorldPoint());
		objectWorldLines[6] = new Line(objectPoints[2].getWorldPoint(),objectPoints[4].getWorldPoint());
		objectWorldLines[7] = new Line(objectPoints[3].getWorldPoint(),objectPoints[4].getWorldPoint());
	}

	@Override
	public void generateLineScreenCoordinates() {
		objectScreenLines[0] = new Line(screenPoints[0], screenPoints[1]);
		objectScreenLines[1] = new Line(screenPoints[1], screenPoints[2]);
		objectScreenLines[2] = new Line(screenPoints[2], screenPoints[3]);
		objectScreenLines[3] = new Line(screenPoints[3], screenPoints[0]);
		objectScreenLines[4] = new Line(screenPoints[0], screenPoints[4]);
		objectScreenLines[5] = new Line(screenPoints[1], screenPoints[4]);
		objectScreenLines[6] = new Line(screenPoints[2], screenPoints[4]);
		objectScreenLines[7] = new Line(screenPoints[3], screenPoints[4]);
	}

	@Override
	public void paintEntity(Graphics g, int frameCentreX, int frameCentreY) {

		Color colour = new Color((float) rgbaColour[0], (float) rgbaColour[1],	(float) rgbaColour[2], (float) rgbaColour[3]);
		
		for(Line lines : objectScreenLines) {
			
			Graphics2D g2d = (Graphics2D) g;
			//g2d.setColor(Color.GREEN);
			g2d.setColor(colour);
			
			g2d.drawLine((int) lines.getStartScreenCoordinates().getX() + frameCentreX, 
					     (int) lines.getStartScreenCoordinates().getY() + frameCentreY, 
					     (int) lines.getEndScreenCoordinates().getX() + frameCentreX,
					     (int) lines.getEndScreenCoordinates().getY() + frameCentreY);
		}
		
	}

}
