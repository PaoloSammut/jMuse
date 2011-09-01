package jMuse;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mathPhysicsEngine.Camera;

import world.LightingList;
import world.ObjectList;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

public class JMuseViewer extends JFrame {
	// attributes
	private int frameWidth = 1300;
	private int frameHeight = 700;
	private int worldHeight = 650;
	private int controlHeight = frameHeight = worldHeight;
	protected static List<ObjectList> objectList = new ArrayList<ObjectList>();
	protected static List<LightingList> lightList = new ArrayList<LightingList>();
	protected static int test1 = 7;
	protected static Camera camera = new Camera();

	private final JFrame jFrameImpl = new JFrame() {
		// Class2 "view" of this class, this inner class
		// has access to all fields/methods of YourClass
	};

	public JFrame asjFrame() {
		return jFrameImpl;
	}

	public JMuseViewer(List<ObjectList> objectList, Camera camera, List<LightingList> lightList) {
		JMuseViewer.objectList = objectList;
		JMuseViewer.lightList = lightList;
		this.camera = camera;
		main(null);
	}

	// DELETE THIS METHOD EVENTUALLY
	public JMuseViewer(List<ObjectList> objectList) {
		JMuseViewer.objectList = objectList;
		main(null);
	}

	public final void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				// create new JME appsettings
				AppSettings settings = new AppSettings(true);
				JMonkeyCanvas canvasApplication = new JMonkeyCanvas();

				canvasApplication.setSettings(settings);
				canvasApplication.createCanvas(); // create canvas!
				JmeCanvasContext ctx = (JmeCanvasContext) canvasApplication.getContext();
				ctx.setSystemListener(canvasApplication);
				Dimension dim = new Dimension(frameWidth, worldHeight); // these should be the same as for the set Width and height above
				ctx.getCanvas().setPreferredSize(dim);

				// Create Swing window which is sitting on the world
				JFrame jFrame = new JFrame("jMuse Music Visualiser");
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel cards;

				// Fill Swing window with canvas and swing components
				JPanel controlPanel = new JPanel();
				JPanel worldPanel = new JPanel();

				jFrame.setMinimumSize(new Dimension(frameWidth, 750));
				jFrame.setPreferredSize(new Dimension(frameWidth, 750));
				jFrame.setMaximumSize(new Dimension(frameWidth, 750));
				worldPanel.setMinimumSize(new Dimension(frameWidth, worldHeight));
				worldPanel.setPreferredSize(new Dimension(frameWidth, worldHeight));
				worldPanel.setMaximumSize(new Dimension(frameWidth, worldHeight));
				controlPanel.setMinimumSize(new Dimension(frameWidth, controlHeight));
				controlPanel.setPreferredSize(new Dimension(frameWidth, controlHeight));
				controlPanel.setMaximumSize(new Dimension(frameWidth, controlHeight));

				cards = new JPanel(new CardLayout());

				cards.add(controlPanel, "The Controllers");
				cards.add(worldPanel, "The World");

				cards.setBackground(Color.GRAY);
				controlPanel.setBackground(Color.DARK_GRAY);
				worldPanel.setBackground(Color.BLACK);

				// add all your Swing components ...
				JButton quitButton = new JButton("Quit");

				quitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("trying to delete");
						System.exit(0);
					}
				});

				controlPanel.add(new JButton("Some Swing Component"));
				controlPanel.add(quitButton);

				worldPanel.add(ctx.getCanvas());
				worldPanel.setVisible(true);
				controlPanel.setVisible(true);

				jFrame.setLayout(new BorderLayout());
				jFrame.add(worldPanel, BorderLayout.NORTH);
				jFrame.add(cards, BorderLayout.CENTER);
				jFrame.setSize(400, 300);
				jFrame.setTitle("Test Frame");
				jFrame.setVisible(true);

				// Display Swing window including JME canvas!
				// jFrame.setVisible(true);
				canvasApplication.startCanvas();
			}
		});
	}

	// public void setFrameWidth(int frameWidth) {
	// this.frameWidth = frameWidth;
	// }
	//
	// public int getFrameHeight() {
	// return frameHeight;
	// }
	//
	// public void setFrameHeight(int frameHeight) {
	// this.frameHeight = frameHeight;
	// }
	//
	// public int getWorldHeight() {
	// return worldHeight;
	// }
	//
	// public void setWorldHeight(int worldHeight) {
	// this.worldHeight = worldHeight;
	// }
	//
	// public int getControlHeight() {
	// return controlHeight;
	// }
	//
	// public void setControlHeight(int controlHeight) {
	// this.controlHeight = controlHeight;
	// }
	//
	// public static List<ObjectList> getObjectList() {
	// return objectList;
	// }
	//
	// public void setObjectList(List<ObjectList> objectList) {
	// JMuseViewer.objectList = objectList;
	// }
	//
	// public int getFrameWidth() {
	// return frameWidth;
	// }

}