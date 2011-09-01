package entities;

import java.awt.Graphics;

import world.World;

public class PaintEntities implements Runnable {
	private World world;
	private Graphics g;
	private int frameCentreX;
	private int frameCentreY; 
	
	public PaintEntities(World world, Graphics g, int frameCentreX, int frameCentreY) {
		this.world = world;
		this.g = g;
		this.frameCentreX = frameCentreX;
		this.frameCentreY = frameCentreY;
	}
	
	
	@Override
	public void run() {
			for (Entity entity : world.getEntities()) {
				entity.regenerateObject();
				entity.paintEntity(g, frameCentreX, frameCentreY);
			}
		
			for (Entity entity : world.getEntities()) {
				entity.regenerateObject();
				entity.paintEntity(g, frameCentreX, frameCentreY);
			}

			
			
			
			
	}

}
