package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mathPhysicsEngine.Line;
import mathPhysicsEngine.ScreenPoint;
import mathPhysicsEngine.WorldPoint;



public class PlaneEntity extends Entity {
	private int sizeOfGrid = 10;
	private int squareSize = 100;
	private int gridPoints = (squareSize * sizeOfGrid);

	public PlaneEntity(double yOffset) {

		noOfVertices = ((sizeOfGrid + 1) * (sizeOfGrid + 1));
		//noOfLines = (sizeOfGrid + 1) * 2;
		noOfLines = sizeOfGrid * sizeOfGrid * sizeOfGrid;
		objectType = "PLANE";
		int x = 0;
		int z = 0;

		objectPoints = new WorldPoint[noOfVertices];
		screenPoints = new ScreenPoint[noOfVertices];
		objectWorldLines = new Line[noOfLines];
		objectScreenLines = new Line[noOfLines];

		// Calculate start end end positions

		int xEnd = ((gridPoints / 2));
		int xStart = xEnd * -1;
		int zEnd = ((gridPoints / 2));
		int zStart = zEnd * -1;
		int c = 0; // counter

		for (x = xStart; x < xEnd + 1; x = x + squareSize) {
			for (z = zStart; z < zEnd + 1; z = z + squareSize) {
				objectPoints[c] = new WorldPoint((double) x, yOffset, (double) z);
				c++;
			}
		}

		regenerateObject();
	}

	@Override
	public void generateVectorLines() {

		int points = (sizeOfGrid * sizeOfGrid);
		int c = 0;
		
		for (WorldPoint vec : objectPoints) {
			for (WorldPoint vec2 : objectPoints) {
				objectWorldLines[c] = new Line(vec.getWorldPoint(), vec2.getWorldPoint());
				c++;
			}
		}
		
		
//		int c = 0;
//		int arrayCounter=0;
//		int startVector = 0;
//		int endVector = sizeOfGrid;
//
//		for (c = 0; c < (noOfLines/2); c++) {
//			objectWorldLines[arrayCounter] = new Line(objectPoints[startVector].getVector(), objectPoints[endVector].getVector());
//			startVector = endVector+1;
//			endVector = endVector + (sizeOfGrid+1);
//			arrayCounter++;
//		}
//
//		startVector = 0;
//		endVector = sizeOfGrid*(sizeOfGrid+1);
//	
//		for (c = 0; c < (noOfLines/2); c++) {
//			objectWorldLines[arrayCounter] = new Line(objectPoints[startVector].getVector(), objectPoints[endVector].getVector());
//			startVector = startVector + 1;
//			endVector = endVector + 1;
//			arrayCounter++;
//		}
		
	}

	@Override
	public void generateLineScreenCoordinates() {
		

		int points = (sizeOfGrid * sizeOfGrid);
		int c = 0;
		
		for (ScreenPoint sp : screenPoints) {
			for (ScreenPoint sp2 : screenPoints) {
				objectScreenLines[c] = new Line(sp, sp2);
				c++;
			}
		}
		
		
		
//		int c = 0;
//		int arrayCounter=0;
//		int startVector = 0;
//		int endVector = sizeOfGrid;
//
//		for (c = 0; c < (noOfLines/2); c++) {
//			objectScreenLines[arrayCounter] = new Line(screenPoints[startVector], screenPoints[endVector]);
//			startVector = endVector+1;
//			endVector = endVector + (sizeOfGrid+1);
//			arrayCounter++;
//		}
//
//		startVector = 0;
//		endVector = sizeOfGrid*(sizeOfGrid+1);
//	
//		for (c = 0; c < (noOfLines/2); c++) {
//			objectScreenLines[arrayCounter] = new Line(screenPoints[startVector], screenPoints[endVector]);
//			startVector = startVector + 1;
//			endVector = endVector + 1;
//			arrayCounter++;
//		}

	}

	@Override
	public void paintEntity(Graphics g, int frameCentreX, int frameCentreY) {
		
		int o = 0;
		for (Line lines : objectScreenLines) {

			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.WHITE);

			g2d.drawLine((int) lines.getStartScreenCoordinates().getX() + frameCentreX, 
						 (int) lines.getStartScreenCoordinates().getY() + frameCentreY, 
						 (int) lines.getEndScreenCoordinates().getX() + frameCentreX,
						 (int) lines.getEndScreenCoordinates().getY() + frameCentreY);

			o++;
		}
	}

}
