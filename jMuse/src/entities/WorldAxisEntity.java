package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mathPhysicsEngine.Line;
import mathPhysicsEngine.ScreenPoint;
import mathPhysicsEngine.WorldPoint;




public class WorldAxisEntity extends Entity {

	public WorldAxisEntity() {
		noOfVertices = 6;
		noOfLines = 3;
		objectType = "AXIS";

		objectPoints = new WorldPoint[noOfVertices];
		screenPoints = new ScreenPoint[noOfVertices];
		objectWorldLines = new Line[noOfLines];
		objectScreenLines = new Line[noOfLines];

		objectPoints[0] = new WorldPoint((double) -100, (double) 0, (double) 0);
		objectPoints[1] = new WorldPoint((double) 100, (double) 0, (double) 0);
		objectPoints[2] = new WorldPoint((double) 0, (double) -100, (double) 0);
		objectPoints[3] = new WorldPoint((double) 0, (double) 100, (double) 0);
		objectPoints[4] = new WorldPoint((double) 0, (double) 0, (double) -100);
		objectPoints[5] = new WorldPoint((double) 0, (double) 0, (double) 100);
		regenerateObject();
	}
	
	
	
	@Override
	public void generateVectorLines() {
		objectWorldLines[0] = new Line(objectPoints[0].getWorldPoint(), objectPoints[1].getWorldPoint());
		objectWorldLines[1] = new Line(objectPoints[2].getWorldPoint(),	objectPoints[3].getWorldPoint());
		objectWorldLines[2] = new Line(objectPoints[4].getWorldPoint(),	objectPoints[5].getWorldPoint());
	}

	@Override
	public void generateLineScreenCoordinates() {
		objectScreenLines[0] = new Line(screenPoints[0], screenPoints[1]);
		objectScreenLines[1] = new Line(screenPoints[2], screenPoints[3]);
		objectScreenLines[2] = new Line(screenPoints[4], screenPoints[5]);

	}

	@Override
	public void paintEntity(Graphics g, int frameCentreX, int frameCentreY) {

		int colour = 0;
		Color[] col = new Color[6];
		col[0] = Color.white;   // X axis
		col[1] = Color.yellow; 	// Y axis
		col[2] = Color.green;	// Z Axis
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(Line lines : objectScreenLines) {

			g2d.setColor(col[colour]);
			
			g2d.drawLine((int) lines.getStartScreenCoordinates().getX() + frameCentreX, 
					     (int) lines.getStartScreenCoordinates().getY() + frameCentreY, 
					     (int) lines.getEndScreenCoordinates().getX() + frameCentreX,
					     (int) lines.getEndScreenCoordinates().getY() + frameCentreY);
			colour++;
		}
	}
	
//	public void rotateXAxis(double d) {
//		// do nothing
//	}

}
