package com.levels.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ToolsPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public enum Element {
		HOME, GROUND, BUILDING, WATER, ROCK, TEMPLE, ROAD, FLOOR, TREE, FOREST1, FOREST2, FLOOR2, FLOOR3, SHOP, STRAW, PREENEMYHOME, CASTLE
	};

	private PreviewPanel pp;
	private JButton home = new JButton();
	private JButton castle = new JButton();
	private JButton shop = new JButton();
	private JButton preEnemyHome = new JButton();
	private JButton straw = new JButton();
	private JButton temple = new JButton();
	private JButton tree = new JButton();
	private JButton ground = new JButton();
	private JButton floor = new JButton();
	private JButton floor2 = new JButton();
	private JButton floor3 = new JButton();
	private JButton road = new JButton();
	private JButton water = new JButton();
	private JButton rock = new JButton();
	private JButton forest1 = new JButton();
	private JButton forest2 = new JButton();

	private JButton grid = new JButton();
	private JButton canc = new JButton();
	private JButton fill = new JButton();
	private JButton clear = new JButton();
	private JButton zoomIn = new JButton();
	private JButton undo = new JButton();

	public static BufferedImageLoader loader = new BufferedImageLoader();
	public static BufferedImage homeImage = loader.loadImage("/home.png");
	public static BufferedImage castleImage = loader.loadImage("/castle.png");
	public static BufferedImage shopImage = loader.loadImage("/shop.png");
	public static BufferedImage preEnemyHomeImage = loader.loadImage("/preEnemyHome.png");
	public static BufferedImage strawImage = loader.loadImage("/straw.png");
	public static BufferedImage templeImage = loader.loadImage("/bigHome.png");
	private static BufferedImage treeImage = loader.loadImage("/three.png");
	private static BufferedImage groundImage = loader.loadImage("/ground.png");
	private static BufferedImage floorImage = loader.loadImage("/floor.png");
	private static BufferedImage floor2Image = loader.loadImage("/floor2.png");
	private static BufferedImage floor3Image = loader.loadImage("/floor3.png");
	private static BufferedImage roadImage = loader.loadImage("/road.png");
	private static BufferedImage waterImage = loader.loadImage("/water.png");
	private static BufferedImage rockImage = loader.loadImage("/rock.png");
	private static BufferedImage forest1Image = loader.loadImage("/forest1.png");
	private static BufferedImage forest2Image = loader.loadImage("/forest2.png");

	private BufferedImage gridImage = loader.loadImage("/grid.png");
	private BufferedImage cancImage = loader.loadImage("/eraser.png");
	private BufferedImage eraseImage = loader.loadImage("/erase.png");
	private BufferedImage fillImage = loader.loadImage("/fill.png");
	private BufferedImage clearImage = loader.loadImage("/clear.png");
	private BufferedImage zoomInImage = loader.loadImage("/zoom.png");
	private BufferedImage undoImage = loader.loadImage("/undo.png");

	private BufferedImage homesLabelImage = loader.loadImage("/labels/homes.png");
	private JLabel homesLabel = new JLabel(new ImageIcon(homesLabelImage));

	private BufferedImage ambientationsLabelImage = loader.loadImage("/labels/ambientation.png");
	private JLabel ambientationsLabel = new JLabel(new ImageIcon(ambientationsLabelImage));

	private BufferedImage floorsLabelImage = loader.loadImage("/labels/floors.png");
	private JLabel floorsLabel = new JLabel(new ImageIcon(floorsLabelImage));

//	private BufferedImage toolsLabelImage = loader.loadImage("/labels/tools.png");
//	private JLabel toolsLabel = new JLabel(new ImageIcon(toolsLabelImage));

	private BufferedImage emptyLabelImage = loader.loadImage("/labels/empty.png");
	private JLabel emptyLabel = new JLabel(new ImageIcon(emptyLabelImage));

	private JPanel elements = new JPanel(new FlowLayout());
	private JPanel tools = new JPanel(new FlowLayout());

	public ToolsPanel(PreviewPanel pp) {
		super();
		this.pp = pp;
		this.setLayout(new BorderLayout());
		setFocusable(true);
		add(elements, BorderLayout.CENTER);
		add(tools, BorderLayout.SOUTH);

		elements.add(homesLabel);
		createButton(home, homeImage, elements);
		createButton(shop, shopImage, elements);
		createButton(preEnemyHome, preEnemyHomeImage, elements);
		createButton(temple, templeImage, elements);
		createButton(castle, castleImage, elements);

		elements.add(ambientationsLabel);
		createButton(tree, treeImage, elements);
		createButton(forest1, forest1Image, elements);
		createButton(forest2, forest2Image, elements);
		createButton(straw, strawImage, elements);
		elements.add(emptyLabel);
		createButton(rock, rockImage, elements);
		createButton(ground, groundImage, elements);
		createButton(water, waterImage, elements);

		elements.add(floorsLabel);
		createButton(floor, floorImage, elements);
		createButton(floor2, floor2Image, elements);
		createButton(floor3, floor3Image, elements);
		createButton(road, roadImage, elements);

		// tools.add(toolsLabel);
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

	public static BufferedImage getCastleImage() {
		return castleImage;
	}

	public static BufferedImage getStrawImage() {
		return strawImage;
	}

	public static BufferedImage getPreEnemyHomeImage() {
		return preEnemyHomeImage;
	}

	public static BufferedImage getShopImage() {
		return shopImage;
	}

	public static BufferedImage getGroundImage() {
		return groundImage;
	}

	public static BufferedImage getFloorImage() {
		return floorImage;
	}

	public static BufferedImage getFloor2Image() {
		return floor2Image;
	}

	public static BufferedImage getFloor3Image() {
		return floor3Image;
	}

	public static BufferedImage getRoadImage() {
		return roadImage;
	}

	public static BufferedImage getWaterImage() {
		return waterImage;
	}

	public static BufferedImage getTempleImage() {
		return templeImage;
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

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == home) {
			pp.paintImage = homeImage;
			pp.currentElement = Element.HOME;
			pp.size = new Dimension(64, 64);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == temple) {
			pp.paintImage = templeImage;
			pp.currentElement = Element.TEMPLE;
			pp.size = new Dimension(128, 96);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == castle) {
			if (!pp.insertedCastle) {
				pp.paintImage = castleImage;
				pp.currentElement = Element.CASTLE;
				pp.size = new Dimension(160, 160);
				pp.remove = false;
				pp.fill = false;
			}
		} else if (e.getSource() == shop) {
			if (!pp.insertedShop) {
				pp.paintImage = shopImage;
				pp.currentElement = Element.SHOP;
				pp.size = new Dimension(64, 64);
				pp.remove = false;
				pp.fill = false;
			}

		} else if (e.getSource() == straw) {
			pp.paintImage = strawImage;
			pp.currentElement = Element.STRAW;
			pp.size = new Dimension(64, 64);
			pp.remove = false;
			pp.fill = false;

		} else if (e.getSource() == preEnemyHome) {
			pp.paintImage = preEnemyHomeImage;
			pp.currentElement = Element.PREENEMYHOME;
			pp.size = new Dimension(96, 96);
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
		} else if (e.getSource() == floor2) {
			pp.paintImage = floor2Image;
			pp.currentElement = Element.FLOOR2;
			pp.size = new Dimension(32, 32);
			pp.remove = false;
			pp.fill = false;
		} else if (e.getSource() == floor3) {
			pp.paintImage = floor3Image;
			pp.currentElement = Element.FLOOR3;
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
			pp.insertedCastle = false;
			pp.insertedShop = false;
			pp.repaint();

		} else if (e.getSource() == undo) {
			if (pp.points.elementAt(pp.points.size() - 1).getElement() == Element.CASTLE)
				pp.insertedCastle = false;
			if (pp.points.elementAt(pp.points.size() - 1).getElement() == Element.SHOP)
				pp.insertedShop = false;

			pp.points.remove(pp.points.size() - 1);
			pp.repaint();

		} else if (e.getSource() == zoomIn) {
			pp.fill = false;
			pp.remove = false;
			pp.above = !pp.above;
			if (pp.above) {
				pp.scale = 16;
				pp.P_WIDTH = EditorConfig.WIDTH / 2;
				pp.P_HEIGHT = EditorConfig.HEIGHT / 2;
				pp.setLocation(0, 0);
				Editor.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				Editor.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			} else {
				pp.scale = 32;
				pp.P_HEIGHT = EditorConfig.WIDTH;
				pp.P_WIDTH = EditorConfig.HEIGHT;
				Editor.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				Editor.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			}
		}
	}

}