package com.levels.editor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.levels.editor.ToolsPanel.Element;

public class Tile implements ICollidable {
	private Point point;
	private BufferedImage image;
	private Element element;
	private Dimension size;
	
	public Tile(Point point, BufferedImage image, Element element, Dimension size) {
		this.point = point;
		this.image = image;
		this.element = element;
		this.size = size;
	}
	
	public Tile(String elemen, Point point2) {
		this.point = point2;
		switch (elemen) {
		case "HOME":
			this.element = Element.HOME;
			image = ToolsPanel.getHomeImage();
			size = new Dimension(64, 64);
			break;
		case "GROUND":
			this.element = Element.GROUND;
			image = ToolsPanel.getGroundImage();
			size = new Dimension(32, 32);
			break;
		case "FLOOR":
			this.element = Element.FLOOR;
			image = ToolsPanel.getFloorImage();
			size = new Dimension(32, 32);
			break;
		case "FLOOR2":
			this.element = Element.FLOOR2;
			image = ToolsPanel.getFloor2Image();
			size = new Dimension(32, 32);
			break;
		case "FLOOR3":
			this.element = Element.FLOOR3;
			image = ToolsPanel.getFloor3Image();
			size = new Dimension(32, 32);
			break;
		case "ROAD":
			this.element = Element.ROAD;
			image = ToolsPanel.getRoadImage();
			size = new Dimension(32, 32);
			break;
		case "TREE":
			this.element = Element.TREE;
			image = ToolsPanel.getThreeImage();
			size = new Dimension(32, 32);
			break;
		case "WATER":
			this.element = Element.WATER;
			image = ToolsPanel.getWaterImage();
			size = new Dimension(32, 32);
			break;
		case "ROCK":
			this.element = Element.ROCK;
			image = ToolsPanel.getRockImage();
			size = new Dimension(32, 32);
			break;
		case "LAMP":
			this.element = Element.LAMP;
			image = ToolsPanel.getLampImage();
			size = new Dimension(16, 48);
			break;
		case "TEMPLE":
			this.element = Element.TEMPLE;
			image = ToolsPanel.getTempleImage();
			size = new Dimension(128, 96);
			break;
		case "FOREST1":
			this.element = Element.FOREST1;
			image = ToolsPanel.getForest1Image();
			size = new Dimension(64, 96);
			break;
		case "FOREST2":
			this.element = Element.FOREST2;
			image = ToolsPanel.getForest2Image();
			size = new Dimension(64, 96);
			break;
		case "SHOP":
			this.element = Element.SHOP;
			image = ToolsPanel.getShopImage();
			size = new Dimension(64, 64);
			break;
		case "STRAW":
			this.element = Element.STRAW;
			image = ToolsPanel.getStrawImage();
			size = new Dimension(64,64);
			break;
		case "PREENEMYHOME":
			this.element = Element.PREENEMYHOME;
			image = ToolsPanel.getPreEnemyHomeImage();
			size = new Dimension(96, 96);
			break;
		case "CASTLE":
			this.element = Element.CASTLE;
			image = ToolsPanel.getCastleImage();
			size = new Dimension(160, 160);
			break;
		default:
			break;
		}
	}
	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	public void setCod(Element element) {
		this.element = element;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	@Override
	public boolean collide(Object e) {
		if (!(point.x > ((Tile) e).getPoint().x
				+ (((Tile) e).getSize().width / 32 * PreviewPanel.scale) / PreviewPanel.scale - 1
				|| ((Tile) e).getPoint().x > point.x + (size.width / 32 * PreviewPanel.scale) / PreviewPanel.scale - 1
				|| point.y > ((Tile) e).getPoint().y
						+ (((Tile) e).getSize().height / 32 * PreviewPanel.scale) / PreviewPanel.scale - 1
				|| ((Tile) e).getPoint().y > point.y + (size.height / 32 * PreviewPanel.scale) / PreviewPanel.scale- 1))
			return true;
		return false;
	}

}
