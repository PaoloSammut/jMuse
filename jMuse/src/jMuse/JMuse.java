package jMuse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mathPhysicsEngine.Camera;
import world.LightingList;
import world.ObjectList;

import com.jme3.math.Vector3f;

@SuppressWarnings("serial")
public class JMuse extends JFrame {

	private enum Filetype {
		bmp, jpg, png, gif, tga, dds
	};

	JTextArea output;
	JScrollPane scrollPane;

	private static int xSize = 800;
	private static int ySize = 600;
	private static int colourFlag = 1; // 1 = colour, 2 = texture, 3 = lighting

	// private static Integer nodeId = 0;
	private static JFormattedTextField numberOfEntities = new JFormattedTextField();
	private static JFormattedTextField numberOfLights = new JFormattedTextField();
	private static JFormattedTextField paletteTransparency = new JFormattedTextField();
	private static List<ObjectList> objectList = new ArrayList<ObjectList>();
	private static List<LightingList> lightList = new ArrayList<LightingList>();

	private static JTextArea textPane = new JTextArea();
	private static JTextArea lightSummaryPane = new JTextArea();

	private static Camera camera = new Camera();
	private static JFormattedTextField cameraX = new JFormattedTextField();
	private static JFormattedTextField cameraY = new JFormattedTextField();
	private static JFormattedTextField cameraZ = new JFormattedTextField();
	private static JFormattedTextField lookAtX = new JFormattedTextField();
	private static JFormattedTextField lookAtY = new JFormattedTextField();
	private static JFormattedTextField lookAtZ = new JFormattedTextField();

	private static String[] entities = getEntityList();
	private static String[] colours = getColourList();
	private static String[] textures = getTextures();

	private static JList entityList = new JList(entities);
	private static JList colourList = new JList(colours);
	private static JList textureList = new JList(textures);

	private static String[] lighting = getLightingList();
	private static JList lightingList = new JList(lighting);
	private static JList lightingColourList = new JList(colours);

	private static JPanel singleColourPanel = new JPanel(null);
	private static JPanel texturedPanel = new JPanel(null);
	private static JPanel lightingPanel = new JPanel(null);

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initUI();
			}
		});
	}

	private static void initUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("jMuse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the menu.
		JMuse demo = new JMuse();
		frame.setJMenuBar(demo.createMenuBar());
		frame.setContentPane(demo.createContentPane());

		// Create the tabbed bar
		JPanel worldPanel = defineWorld();
		JPanel cameraPanel = defineCamera();
		JPanel lightPanel = defineLight();
		JPanel objectPanel = defineObjects();
		JPanel spaceTimePanel = defineSpaceTime();
		JPanel physicsPanel = definePhysics();
		JPanel magickPanel = defineMagick();

		// Lay them out.
		Border padding = BorderFactory.createEmptyBorder(20, 20, 5, 20);
		worldPanel.setBorder(padding);
		cameraPanel.setBorder(padding);
		lightPanel.setBorder(padding);
		objectPanel.setBorder(padding);
		spaceTimePanel.setBorder(padding);
		physicsPanel.setBorder(padding);
		magickPanel.setBorder(padding);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("World", null, worldPanel, "Define the world");
		tabbedPane.addTab("Light", null, lightPanel, "Define your light sources");
		tabbedPane.addTab("Camera", null, cameraPanel, "Define your camera");
		tabbedPane.addTab("Objects", null, objectPanel, "Define the objects within the world");
		tabbedPane.addTab("Animation", null, spaceTimePanel, "Set up animation parameters");
		tabbedPane.addTab("Physics", null, physicsPanel, "Real-world \"physics\" used to model movement");
		tabbedPane.addTab("Magic", null, magickPanel, "All the special effects not covered by the others");

		// TODO - this changes on all tabs; change it so that it only works on the Animation tab
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (tabbedPane.getSelectedIndex() == 4) {
					defineSpaceTime();
				}

			}
		});

		frame.add(tabbedPane, BorderLayout.CENTER);

		// Display the window.
		frame.setSize(getxSize(), getySize());
		frame.setVisible(true);
	}

	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// Create a scrolled text area.
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);

		// Add the text area to the content pane.
		contentPane.add(scrollPane, BorderLayout.CENTER);

		return contentPane;
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		fileMenu.getAccessibleContext().setAccessibleDescription("jMuse file menu");
		menuBar.add(fileMenu);

		// a group of JMenuItems

		menuItem = new JMenuItem("View World", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("View or edit the constants which make up the world");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCamera();
						JMuseViewer jMuseViewer = new JMuseViewer(objectList, camera, lightList);
						jMuseViewer.asjFrame().setVisible(true);
					}
				});
			}
		});
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("View or edit the constants which make up the world");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		fileMenu.add(menuItem);

		// Build second menu in the menu bar.
		fileMenu = new JMenu("Edit");
		fileMenu.setMnemonic(KeyEvent.VK_N);
		fileMenu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		menuBar.add(fileMenu);

		menuItem = new JMenuItem("View / Edit world constants", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("View or edit the constants which make up the world");
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Manage import / export preferences", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("View or edit the constants which make up the world");
		fileMenu.add(menuItem);

		return menuBar;
	}

	private static JPanel defineWorld() {
		JPanel worldPanel = new JPanel(new BorderLayout());
		return worldPanel;
	}

	private static JPanel defineCamera() {
		JPanel cameraPanel = new JPanel(null);

		JLabel cameraPositionLabel = new JLabel("Camera Position");
		JLabel cameraLookAtLabel = new JLabel("Camera Direction");

		cameraPositionLabel.setVerticalTextPosition(JLabel.BOTTOM);
		cameraLookAtLabel.setVerticalTextPosition(JLabel.BOTTOM);

		cameraPositionLabel.setBounds(205, 20, 250, 30);
		cameraLookAtLabel.setBounds(205, 50, 250, 30);

		cameraX.setValue(new Float(0));
		cameraX.setColumns(10);
		cameraX.setBounds(10, 20, 60, 30);
		cameraY.setValue(new Float(0));
		cameraY.setColumns(10);
		cameraY.setBounds(75, 20, 60, 30);
		cameraZ.setValue(new Float(10));
		cameraZ.setColumns(10);
		cameraZ.setBounds(140, 20, 60, 30);

		lookAtX.setValue(new Float(0));
		lookAtX.setColumns(10);
		lookAtX.setBounds(10, 50, 60, 30);
		lookAtY.setValue(new Float(0));
		lookAtY.setColumns(10);
		lookAtY.setBounds(75, 50, 60, 30);
		lookAtZ.setValue(new Float(-1));
		lookAtZ.setColumns(10);
		lookAtZ.setBounds(140, 50, 60, 30);

		cameraPanel.add(cameraX);
		cameraPanel.add(cameraY);
		cameraPanel.add(cameraZ);
		cameraPanel.add(lookAtX);
		cameraPanel.add(lookAtY);
		cameraPanel.add(lookAtZ);
		cameraPanel.add(cameraPositionLabel);
		cameraPanel.add(cameraLookAtLabel);
		cameraX.getValue();
		return cameraPanel;
	}

	private void setCamera() {
		camera.setCameraLookAt(new Vector3f(getCameraX(), getCameraY(), getCameraZ()));
		camera.setCameraPosition(new Vector3f(getLookAtX(), getLookAtY(), getLookAtZ()));
	}

	private static JPanel defineLight() {
		JPanel lightPanel = new JPanel(null);

		JScrollPane lightingListPane = new JScrollPane();
		JScrollPane lightingColourListPane = new JScrollPane();
		JScrollPane lightingSummaryPane = new JScrollPane();
		JButton addLightsButton = new JButton("Add");
		JButton clearLightsButton = new JButton("Clear");

		// Set up lights list and create a scrollPane to display ui
		lightingList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
				}
			}
		});

		lightingList.setSelectedIndex(0); // 2 should be the default
		lightingListPane.getViewport().add(lightingList);
		lightingListPane.setBounds(20, 20, 200, 60);

		// Set up colour list and create a scrollPane to display ut
		lightingColourList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
				}
			}
		});

		lightingColourList.setSelectedIndex(0);
		lightingColourListPane.getViewport().add(lightingColourList);
		lightingColourListPane.setBounds(220, 20, 200, 60);

		numberOfLights.setValue(new Integer(1)); // 5 should be default
		numberOfLights.setColumns(10);
		numberOfLights.setBounds(420, 20, 60, 30);

		// Log pane lists what to create (contains textPane)
		lightingSummaryPane.setBounds(20, 90, 250, 80);
		lightingSummaryPane.getViewport().add(lightSummaryPane);
		lightSummaryPane.setEditable(false);
		lightSummaryPane.setText("Lights being created in World");
		lightSummaryPane.setBounds(20, 90, 250, 80);

		// Add entity button
		addLightsButton.setBounds(482, 20, 80, 30);
		addLightsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lightSummaryPane.setText(lightSummaryPane.getText() + "\n\r " + lightingList.getSelectedValue() + " " + lightingColourList.getSelectedValue()
						+ " " + numberOfLights.getValue() + " ");

				LightingList lL = new LightingList(lightingList.getSelectedValue().toString(), lightingColourList.getSelectedValue().toString(),
						(Integer) numberOfLights.getValue());

				lightList.add(lL);
			}
		});

		// Clear list button
		clearLightsButton.setBounds(565, 20, 80, 30);
		clearLightsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				lightSummaryPane.setText("Lights being created in World");
				lightList.removeAll(lightList);

			};
		});

		lightPanel.add(lightingListPane);
		lightPanel.add(lightingColourListPane);
		lightPanel.add(numberOfLights);
		lightPanel.add(lightingSummaryPane);
		lightPanel.add(addLightsButton);
		lightPanel.add(clearLightsButton);

		return lightPanel;
	}

	private static JPanel defineObjects() {
		final JPanel objectPanel = new JPanel(null);
		JLabel objectCreationParametersLabel = new JLabel("Object Creation Parameters");
		final int colourFlag = 1; // 1 = colour, 2 = texture, 3 = lighting

		// First describe the scroll bars = not all of these will be visible by default
		// Set up entity list and create a scrollPane to display ui
		JScrollPane entityListPane = new JScrollPane();
		entityList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
				}
			}
		});

		entityList.setSelectedIndex(2);
		entityListPane.getViewport().add(entityList);
		entityListPane.setBounds(20, 20, 200, 60);

		// Set up colour list and create a scrollPane to display ut
		JScrollPane colourListPane = new JScrollPane();
		colourList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
				}
			}
		});

		colourList.setSelectedIndex(0);
		colourListPane.getViewport().add(colourList);
		colourListPane.setBounds(10, 10, 200, 60);

		// set up the texture list
		JScrollPane textureListPane = new JScrollPane();
		textureList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
				}
			}
		});

		final JCheckBox wireframeCheckbox = new JCheckBox("Wireframe", false);
		wireframeCheckbox.setBounds(220, 45, 20, 20);
		wireframeCheckbox.setToolTipText("Check for wireframe mode");
		singleColourPanel.setVisible(true);
		singleColourPanel.setBounds(220, 90, 400, 200);
		singleColourPanel.add(colourListPane);
		singleColourPanel.add(wireframeCheckbox);

		singleColourPanel.add(paletteTransparency);

		// texturedPanel.add(textureListPane); visible on approporate radiobutton
		textureList.setSelectedIndex(0);
		texturedPanel.setVisible(false);
		texturedPanel.setBounds(220, 90, 400, 200);
		texturedPanel.add(textureListPane);
		textureListPane.getViewport().add(textureList);
		textureListPane.setBounds(10, 10, 300, 100);

		// visible by default and on appropriate radio button

		// visible on appropriate radio button
		// lightingPanel.add(textureListPane);
		lightingPanel.setVisible(false);
		lightingPanel.setBounds(220, 90, 400, 200);
		// lightingPanel.add(textureListPane);

		// value fields

		// Now set up text box to add number of entities (Integer only)
		// JFormattedTextField numberOfEntities = new JFormattedTextField();
		numberOfEntities.setValue(new Integer(50)); // 5 should be default
		numberOfEntities.setColumns(10);
		numberOfEntities.setBounds(420, 20, 60, 30);

		// Now set up text box to add number of entities (Integer only)
		// JFormattedTextField numberOfEntities = new JFormattedTextField();
		paletteTransparency.setValue(new Float(1)); // 5 should be default
		paletteTransparency.setColumns(10);
		paletteTransparency.setBounds(215, 10, 60, 30);
		paletteTransparency.setToolTipText("Enter the transparency of the object; 1 = fully opaque, 0 = fully transparent");

		JScrollPane logPane = new JScrollPane();

		// Log pane lists what to create (contains textPane)
		logPane.setBounds(20, 400, 600, 100);
		logPane.getViewport().add(textPane);
		textPane.setEditable(false);
		textPane.setText("Objects being created in World");

		// radiobuttons describing shading scheme
		// Create the radio buttons.
		JRadioButton singleColour = new JRadioButton("SingleColour");
		singleColour.setActionCommand("SingleColour");
		singleColour.setSelected(true);

		JRadioButton simpleShadedButton = new JRadioButton("WallPapered");
		simpleShadedButton.setActionCommand("WallPapered");

		JRadioButton lightingModelButton = new JRadioButton("Lighting Model");
		lightingModelButton.setActionCommand("Lighting Model");

		// Group the radio buttons.
		ButtonGroup shadingRadioGroup = new ButtonGroup();
		shadingRadioGroup.add(singleColour);
		shadingRadioGroup.add(simpleShadedButton);
		shadingRadioGroup.add(lightingModelButton);

		// Put the radio buttons in a column in a panel.
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(singleColour);
		radioPanel.add(simpleShadedButton);
		radioPanel.add(lightingModelButton);

		radioPanel.setBounds(10, 80, 200, 210);
		objectPanel.add(radioPanel);
		// end of radio buttons

		// Register a listener for the radio buttons.
		singleColour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				singleColourPanel.setVisible(true);
				texturedPanel.setVisible(false);
				lightingPanel.setVisible(false);
				setColourFlag(1);
			};
		});

		simpleShadedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				singleColourPanel.setVisible(false);
				texturedPanel.setVisible(true);
				lightingPanel.setVisible(false);
				setColourFlag(2);
			};
		});
		lightingModelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				singleColourPanel.setVisible(false);
				texturedPanel.setVisible(false);
				lightingPanel.setVisible(true);
				setColourFlag(3);
			};
		});

		// Add entity button
		JButton addButton = new JButton("Add");

		addButton.setBounds(630, 435, 80, 30);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textPane.setText(textPane.getText() + "\n\r node:" + entityList.getSelectedValue() + "  " + numberOfEntities.getValue() + " texture (");

				ObjectList oL = new ObjectList(entityList.getSelectedValue().toString(), (Integer) numberOfEntities.getValue(), getColourFlag());
				switch (getColourFlag()) {
				case 1:

					textPane.setText(textPane.getText() + "colour model, wireframe = " + wireframeCheckbox.isSelected() + "  Colour: "
							+ colourList.getSelectedValue().toString() + "  transparency:  " + paletteTransparency.getValue());
					oL.setWireframe(wireframeCheckbox.isSelected());
					oL.setColour(colourList.getSelectedValue().toString());
					oL.setTransparency((Float) paletteTransparency.getValue());
					break;
				case 2:

					textPane.setText(textPane.getText() + "wallpaper model: " + textureList.getSelectedValue().toString());
					oL.setWallPaper(textureList.getSelectedValue().toString());
					oL.setColour(colourList.getSelectedValue().toString());
					oL.setTransparency((Float) paletteTransparency.getValue());
					break;
				case 3:
					textPane.setText(textPane.getText() + "lighting Mode; <GET LIGHTING DETAILS>, transparency: " + paletteTransparency.getValue());
					oL.setTransparency((Float) paletteTransparency.getValue());
					oL.setColour(colourList.getSelectedValue().toString());
					;
				default:
					System.out.println("unknown colourFlag state");
					break;
				} // end of switch
				textPane.setText(textPane.getText() + ")");
				objectList.add(oL);
			}
		});

		// Clear list button
		JButton clearButton = new JButton("Clear");
		clearButton.setBounds(630, 400, 80, 30);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textPane.setText("Objects being created in World");
				objectList.removeAll(objectList);
			};
		});

		objectPanel.add(objectCreationParametersLabel);
		objectPanel.add(entityListPane);
		objectPanel.add(singleColourPanel);
		objectPanel.add(lightingPanel);
		objectPanel.add(texturedPanel);
		objectPanel.add(logPane);
		objectPanel.add(numberOfEntities);
		// objectPanel.add(wireframeCheckbox);
		objectPanel.add(addButton);
		objectPanel.add(clearButton);

		return objectPanel;
	}

	// animation
	private static JPanel defineSpaceTime() {
		JPanel spaceTimePanel = new JPanel(new BorderLayout());

		JScrollPane nodeListPane = new JScrollPane();

		List<String> nodes = getNodeList();
		System.out.println("nodes: " + nodes);
		// JList nodeList = new JList(nodes);
		//
		// nodeList.addListSelectionListener(new ListSelectionListener() {
		// public void valueChanged(ListSelectionEvent e) {
		// if (!e.getValueIsAdjusting()) {
		// }
		// }
		// });
		//
		// //nodeList.setSelectedIndex(2);
		// nodeListPane.getViewport().add(nodeList);
		// nodeListPane.setBounds(20, 20, 200, 60);
		//
		// spaceTimePanel.add(nodeListPane);

		return spaceTimePanel;
	}

	private static List<String> getNodeList() {
		List<String> nodes = new ArrayList<String>();
		System.out.println("--test start" + objectList);
		// TODO objectList.get

		for (ObjectList entities : objectList) {
			nodes.add(entities.getName());
			System.out.println("--test obj" + entities.getName());
		}

		for (LightingList entities : lightList) {
			nodes.add(entities.getName());
			System.out.println("--test lig" + entities.getName());
		}
		System.out.println("--test end");
		return nodes;
	}

	private static JPanel definePhysics() {
		JPanel worldPanel = new JPanel(new BorderLayout());
		return worldPanel;
	}

	private static JPanel defineMagick() {
		JPanel magickPanel = new JPanel(new BorderLayout());
		return magickPanel;
	}

	private static String[] getEntityList() {
		String[] entities = new String[10];

		entities[0] = "Cube";
		entities[1] = "Pyramid";
		entities[2] = "Sphere";
		entities[3] = "Cuboid";
		entities[4] = "Four";
		entities[5] = "Five";
		entities[6] = "Six";
		entities[7] = "Seven";
		entities[8] = "Eight";
		entities[9] = "Nine";

		return entities;
	}

	private static String[] getColourList() {
		String[] colours = new String[14];
		colours[0] = "RANDOM";
		colours[1] = "WHITE";
		colours[2] = "YELLOW";
		colours[3] = "RED";
		colours[4] = "BLUE";
		colours[5] = "GREEN";
		colours[6] = "CYAN";
		colours[7] = "GRAY";
		colours[8] = "ORANGE";
		colours[9] = "MAGENTA";
		colours[10] = "PINK";
		colours[11] = "LIGHT_GRAY";
		colours[12] = "DARK_GRAY";
		colours[13] = "BLACK";
		return colours;
	}

	private static String[] getLightingList() {
		String[] lightSources = new String[4];
		lightSources[0] = "POINTLIGHT";
		lightSources[1] = "DIRECTIONLIGHT";
		lightSources[2] = "AMBIENTLIGHT";
		lightSources[3] = "SPOTLIGHT";
		return lightSources;
	}

	private static String[] getTextures() {
		// return list of BMP, JPG, PNG, GIF, TGA and DDS. files in relevant folder
		List<String> files = new ArrayList<String>();

		File dir = new File("C:\\Cloud\\Dropbox\\IndigoWorkspace\\jMuse\\Assets\\Interface\\Logo"); // hard coded for now
		String[] fileList = dir.list();
		if (fileList == null) {
			System.out.println("Specified directory does not exist or is not a directory.");
		} else {
			for (int i = 0; i < fileList.length; i++) {
				for (Filetype ft : Filetype.values()) {
					if (fileList[i].toLowerCase().endsWith(ft.toString())) {
						files.add(fileList[i]);
					}
				}
			}
		}

		// annoyingly dropdowns want an array, not a list - i think this is a crappy bit of code; needs work
		String[] returnList = new String[files.size()];
		for (int i = 0; i < files.size(); i++) {
			returnList[i] = files.get(i);
		}

		return returnList;
	}

	public static float getCameraX() {
		return (Float) cameraX.getValue();
	}

	public static void setCameraX(JFormattedTextField cameraX) {
		JMuse.cameraX = cameraX;
	}

	public static float getCameraY() {
		return (Float) cameraY.getValue();
	}

	public static void setCameraY(JFormattedTextField cameraY) {
		JMuse.cameraY = cameraY;
	}

	public static float getCameraZ() {
		return (Float) cameraZ.getValue();
	}

	public static void setCameraZ(JFormattedTextField cameraZ) {
		JMuse.cameraZ = cameraZ;
	}

	public static float getLookAtX() {
		return (Float) lookAtX.getValue();
	}

	public static void setLookAtX(JFormattedTextField lookAtX) {
		JMuse.lookAtX = lookAtX;
	}

	public static float getLookAtY() {
		return (Float) lookAtY.getValue();
	}

	public static void setLookAtY(JFormattedTextField lookAtY) {
		JMuse.lookAtY = lookAtY;
	}

	public static float getLookAtZ() {
		return (Float) lookAtZ.getValue();
	}

	public static void setLookAtZ(JFormattedTextField lookAtZ) {
		JMuse.lookAtZ = lookAtZ;
	}

	public static int getColourFlag() {
		return colourFlag;
	}

	public static void setColourFlag(int cF) {
		colourFlag = cF;
	}

	public static int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public static int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}
}