package com.levels.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ToolsPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public enum Element {
		HOME, GROUND, BUILDING, WATER, ROCK, BIGHOME, ROAD, FLOOR, TREE, FOREST1, FOREST2, TABLE
	};

	private PreviewPanel pp;
	private JButton home = new JButton();
	private JButton bigHome = new JButton();
	private JButton tree = new JButton();
	private JButton ground = new JButton();
	private JButton floor = new JButton();
	private JButton road = new JButton();
	private JButton water = new JButton();
	private JButton rock = new JButton();
	private JButton forest1 = new JButton();
	private JButton forest2 = new JButton();
	private JButton table = new JButton();

	private JButton grid = new JButton();
	private JButton canc = new JButton();
	private JButton fill = new JButton();
	private JButton clear = new JButton();
	private JButton zoomIn = new JButton();
	private JButton undo = new JButton();

	public static BufferedImageLoader loader = new BufferedImageLoader();
	public static BufferedImage homeImage = loader.loadImage("/home.png");
	public static BufferedImage bigHomeImage = loader.loadImage("/bigHome.png");
	private static BufferedImage treeImage = loader.loadImage("/three.png");
	private static BufferedImage groundImage = loader.loadImage("/ground.png");
	private static BufferedImage floorImage = loader.loadImage("/floor.png");
	private static BufferedImage roadImage = loader.loadImage("/road.png");
	private static BufferedImage waterImage = loader.loadImage("/water.png");
	private static BufferedImage rockImage = loader.loadImage("/rock.png");
	private static BufferedImage forest1Image = loader.loadImage("/forest1.png");
	private static BufferedImage forest2Image = loader.loadImage("/forest2.png");
	private static BufferedImage tableImage = loader.loadImage("/table.png");

	private BufferedImage gridImage = loader.loadImage("/grid.png");
	private BufferedImage cancImage = loader.loadImage("/eraser.png");
	private BufferedImage eraseImage = loader.loadImage("/erase.png");
	private BufferedImage fillImage = loader.loadImage("/fill.png");
	private BufferedImage clearImage = loader.loadImage("/clear.png");
	private BufferedImage zoomInImage = loader.loadImage("/zoom.png");
	private BufferedImage undoImage = loader.loadImage("/undo.png");

	private JPanel elements = new JPanel(new FlowLayout());
	private JPanel tools = new JPanel(new FlowLayout());

	public ToolsPanel(PreviewPanel pp) {
		super();
		this.pp = pp;
		this.setLayout(new BorderLayout());
		setFocusable(true);
		add(elements, BorderLayout.CENTER);
		add(tools, BorderLayout.SOUTH);

		createButton(home, homeImage, elements);
		createButton(bigHome, bigHomeImage, elements);
		createButton(tree, treeImage, elements);
		createButton(rock, rockImage, elements);
		createButton(ground, groundImage, elements);
		createButton(floor, floorImage, elements);
		createButton(water, waterImage, elements);
		createButton(road, roadImage, elements);
		createButton(forest1, forest1Image, elements);
		createButton(forest2, forest2Image, elements);
		createButton(table, tableImage, elements);

		createButton(grid, gridImage, tools);
		createButton(canc, cancImage, tools);
		createButton(undo, undoImage, tools);
		createButton(fill, fillImage, tools);
		createButton(clear, clearImage, tools);
		createButton(zoomIn, zoomInImage, tools);

		pp.paintImage = homeImage;
		pp.currentElement = Element.HOME;
		pp.size = new Dimension(PreviewPanel.scale * 2, PreviewPanel.scale * 2);
	}

	void createButton(JButton button, BufferedImage image, JPanel panel) {
		button.setIcon(new ImageIcon(image.getScaledInstance(32, 32, 0)));
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(40, 40));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		panel.add(button);
	}

	public JButton getHome() {
		return home;
	}

	public void setHome(JButton home) {
		this.home = home;
	}

	public JButton getTree() {
		return tree;
	}

	public void setThree(JButton tree) {
		this.tree = tree;
	}

	public JButton getGround() {
		return ground;
	}

	public void setGround(JButton ground) {
		this.ground = ground;
	}

	public JButton getWater() {
		return water;
	}

	public void setWater(JButton water) {
		this.water = water;
	}

	public JButton getRock() {
		return rock;
	}

	public void setRock(JButton rock) {
		this.rock = rock;
	}

	public static BufferedImage getHomeImage() {
		return homeImage;
	}

	public static void setHomeImage(BufferedImage homeImage) {
		ToolsPanel.homeImage = homeImage;
	}

	public static BufferedImage getThreeImage() {
		return treeImage;
	}

	public static BufferedImage getGroundImage() {
		return groundImage;
	}

	public static BufferedImage getRoadImage() {
		return roadImage;
	}

	public static BufferedImage getWaterImage() {
		return waterImage;
	}

	public static BufferedImage getBigHomeImage() {
		return bigHomeImage;
	}

	public static BufferedImage getRockImage() {
		return rockImage;
	}

	public static BufferedImage getForest1Image() {
		return forest1Image;
	}

	public static BufferedImage getForest2Image() {
		return forest2Image;
	}

	public static BufferedImage getTableImage() {
		return tableImage;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == home) {
			pp.paintImage = homeImage;
			pp.currentElement = Element.HOME;
			pp.size = new Dimension(64, 64);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == bigHome) {
			pp.paintImage = bigHomeImage;
			pp.currentElement = Element.BIGHOME;
			pp.size = new Dimension(128, 96);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == tree) {
			pp.paintImage = treeImage;
			pp.currentElement = Element.TREE;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == ground) {
			pp.paintImage = groundImage; // GROUND IMAGE
			pp.currentElement = Element.GROUND;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == road) {
			pp.paintImage = roadImage; // GROUND IMAGE
			pp.currentElement = Element.ROAD;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == floor) {
			pp.paintImage = floorImage;
			pp.currentElement = Element.FLOOR;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == water) {
			pp.paintImage = waterImage;
			pp.currentElement = Element.WATER;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == rock) {
			pp.paintImage = rockImage;
			pp.currentElement = Element.ROCK;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == forest1) {
			pp.paintImage = forest1Image;
			pp.currentElement = Element.FOREST1;
			pp.size = new Dimension(64, 96);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == forest2) {
			pp.paintImage = forest2Image;
			pp.currentElement = Element.FOREST2;
			pp.size = new Dimension(64, 96);
			pp.remove = false;
			pp.fill = false;
			
		} else if (e.getSource() == table) {
			pp.paintImage = tableImage;
			pp.currentElement = Element.TABLE;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == grid) {
			pp.fill = false;
			pp.remove = false;
			pp.grid = !pp.grid;

		} else if (e.getSource() == fill) {
			pp.fill = true;
			pp.remove = false;
			pp.fillTiles();

		} else if (e.getSource() == canc) {
			pp.paintImage = eraseImage;
			pp.remove = true;
			pp.fill = false;

		} else if (e.getSource() == clear) {
			pp.points.clear();
			pp.repaint();

		} else if (e.getSource() == undo) {
			pp.points.remove(pp.points.size() - 1);
			pp.repaint();

		} else if (e.getSource() == zoomIn) {
			pp.fill = false;
			pp.remove = false;
			pp.above = !pp.above;
			if (pp.above) {
				pp.scale = 16;
				pp.P_WIDTH = 720;
				pp.P_HEIGHT = 480;
				pp.setLocation(0, 0);
				Editor.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				Editor.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			} else {
				pp.scale = 32;
				pp.P_HEIGHT = 960;
				pp.P_WIDTH = 1440;
				Editor.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				Editor.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			}
		}
	}
}