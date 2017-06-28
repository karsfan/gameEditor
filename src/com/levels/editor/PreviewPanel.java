package com.levels.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;
import com.levels.editor.ToolsPanel.Element;

public class PreviewPanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	public int P_WIDTH;
	public int P_HEIGHT;
	public static int scale;

	public static Vector<Tile> points;
	public BufferedImage paintImage;
	public Element currentElement;
	public Dimension size;
	public boolean remove;
	public boolean fill;
	public boolean grid;
	public boolean above;
	Tile tiledisegnare;

	public PreviewPanel() {
		super();
		setFocusable(true);
		requestFocusInWindow(true);
		addMouseListener(this);
		addMouseMotionListener(this);

		points = new Vector<Tile>();

		paintImage = null;
		currentElement = null;
		size = null;
		tiledisegnare = new Tile("HOME", new Point(1, 1));
		P_WIDTH = EditorConfig.WIDTH;
		P_HEIGHT = EditorConfig.HEIGHT;
		scale = 32;

		remove = false;
		fill = false;
		grid = true;
		above = false;

		setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
		setMinimumSize(new Dimension(1020, 700));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray);
		int w = P_WIDTH;
		int h = P_HEIGHT;
		if (!above) {
			g.fillRect(0, 0, w, h);
			for (int i = 0; i < points.size(); i++) {
				Tile tmp = points.get(i);
				g.drawImage(tmp.getImage(), tmp.getPoint().x * scale, tmp.getPoint().y * scale,
						tmp.getSize().width / 32 * scale, tmp.getSize().height / 32 * scale, null);
			}

			if (grid) {
				for (int i = 0; i <= P_HEIGHT; i += scale) {
					g.setColor(new Color(150, 150, 150));
					g.fillRect(0, i, P_WIDTH, 1);
				}
				for (int i = 0; i <= P_WIDTH; i += scale) {
					g.setColor(new Color(150, 150, 150));
					g.fillRect(i, 0, 1, P_HEIGHT);
				}

			}
			g.drawImage(tiledisegnare.getImage(), tiledisegnare.getPoint().x * scale,
					tiledisegnare.getPoint().y * scale, tiledisegnare.getSize().width / 32 * scale,
					tiledisegnare.getSize().height / 32 * scale, null);
		}
		if (above) {
			// calcolo lo spazio che serve per centrare la mappa nello
			// scrollpane
			int x = ((Editor.scrollPane.getWidth() - P_WIDTH) / 2);
			int y = ((Editor.scrollPane.getHeight() - P_HEIGHT) / 2);
			setAlignmentX(0);
			setAlignmentY(0);
			g.setColor(new Color(90, 100, 100));
			g.fillRect(0, 0, P_WIDTH * 2, P_HEIGHT * 2);
			g.setColor(Color.gray);
			g.fillRect(x, y, w, h);

			for (int i = 0; i < points.size(); i++) {
				Tile tmp = points.get(i);
				g.drawImage(tmp.getImage(), tmp.getPoint().x * scale + x, tmp.getPoint().y * scale + y,
						tmp.getSize().width / 32 * scale, tmp.getSize().height / 32 * scale, null);
			}
			if (grid) {
				for (int i = y; i <= P_HEIGHT + y; i += scale) {
					g.setColor(new Color(150, 150, 150));
					g.fillRect(x, i, P_WIDTH, 1);
				}
				for (int i = x; i <= P_WIDTH + x; i += scale) {
					g.setColor(new Color(150, 150, 150));
					g.fillRect(i, y, 1, P_HEIGHT);
				}
			}
			if (!(tiledisegnare.getPoint().x + tiledisegnare.getSize().getWidth() / 32 - 1 > P_WIDTH / scale
					|| tiledisegnare.getPoint().y + tiledisegnare.getSize().getHeight() / 32 - 1 > P_HEIGHT / scale
					|| tiledisegnare.getPoint().x < 0 || tiledisegnare.getPoint().getY() < 0))
				g.drawImage(tiledisegnare.getImage(), tiledisegnare.getPoint().x * scale + x,
						tiledisegnare.getPoint().y * scale + y, tiledisegnare.getSize().width / 32 * scale,
						tiledisegnare.getSize().height / 32 * scale, null);
		}
		repaint();
	}

	public static void addTile(String element, Point point) {
		Tile home = new Tile(element, point);
		points.add(home);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePressed(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (above) {
			x -= ((Editor.scrollPane.getWidth() - P_WIDTH) / 2);
			y -= ((Editor.scrollPane.getHeight() - P_HEIGHT) / 2);
		}
		if (x < 0 || y < 0 || x > P_WIDTH || y > P_HEIGHT)
			return;
		Point p = clickToGrid(x, y);
		Tile cp = new Tile(p, paintImage, currentElement, size);

		if (remove) // bisogna eliminare il tiledadisegnare
			delete(clickToGrid(x, y));
		else if (fill)
			fillTiles();
		else if (cp.getElement() == Element.GROUND) {
			if (!duplicate(cp))
				points.add(0, cp);
		} else if (!existTile(cp))
			if (!duplicate(cp))
				points.add(cp);

		repaint();
	}

	void fillTiles() {
		if (currentElement == Element.GROUND || currentElement == Element.FLOOR || currentElement == Element.FLOOR2
				|| currentElement == Element.FLOOR3) {
			for (int i = 0; i < P_WIDTH * size.width / scale; i += size.width)
				for (int j = 0; j < P_HEIGHT * size.height / scale; j += size.height)
					if (!duplicate(
							(new Tile(new Point(i / size.width, j / size.height), paintImage, currentElement, size))))
						points.add(0,
								new Tile(new Point(i / size.width, j / size.height), paintImage, currentElement, size));
			return;
		}

		for (int i = 0; i < P_WIDTH * size.width / scale; i += size.width)
			for (int j = 0; j < P_HEIGHT * size.height / scale; j += size.height)
				if (!existTile(new Tile(new Point(i / size.width, j / size.height), paintImage, currentElement, size)))
					points.add(new Tile(new Point(i / size.width, j / size.height), paintImage, currentElement, size));

		repaint();
	}

	private boolean existTile(Tile tile) {
		// controllo che siano completamente all'interno della griglia
		if (tile.getPoint().x + tile.getSize().getWidth() / 32 > P_WIDTH / scale
				|| tile.getPoint().y + tile.getSize().getHeight() / 32 > P_HEIGHT / scale)
			return true;
		// controllo che non sto mettendo alberi sul pavimento
		for (int i = 0; i < points.size(); i++) {
			Tile tmp = points.get(i);
			if (tile.collide(tmp) && (tmp.getElement() != Element.GROUND)
					&& !((tile.getElement() != Element.TREE && tile.getElement() != Element.FOREST1
							&& tile.getElement() != Element.FOREST2)
							&& (tmp.getElement() == Element.ROAD || tmp.getElement() == Element.FLOOR
									|| tmp.getElement() == Element.FLOOR2 || tmp.getElement() == Element.FLOOR3)))
				return true;
		}
		return false;
	}

	private Point clickToGrid(int x, int y) {
		int px = x;
		int py = y;

		px = px / scale;
		py = py / scale;

		return new Point(px, py);
	}

	public void delete(Point point) {
		if (above) {
			for (int i = points.size() - 1; i >= 0; i--) {
				Tile tmp = points.get(i);
				if (point.getX() <= (tmp.getSize().getWidth() / (scale * 2) + tmp.getPoint().getX() - 1)
						&& point.getY() <= (tmp.getSize().getHeight() / (scale * 2) + tmp.getPoint().getY() - 1)
						&& point.getX() >= tmp.getPoint().getX() && point.getY() >= tmp.getPoint().getY()) {
					points.remove(i);
					break;
				}
			}
		} else if (!above) {
			for (int i = points.size() - 1; i >= 0; i--) {
				Tile tmp = points.get(i);
				if (point.getX() <= (tmp.getSize().getWidth() / scale + tmp.getPoint().getX() - 1)
						&& point.getY() <= (tmp.getSize().getHeight() / scale + tmp.getPoint().getY() - 1)
						&& point.getX() >= tmp.getPoint().getX() && point.getY() >= tmp.getPoint().getY()) {
					points.remove(i);
					break;
				}
			}
		}
	}

	public boolean duplicate(Tile tile) {
		for (int i = 0; i < points.size(); i++) {
			Tile tmp = points.get(i);
			if (tmp.getElement() == tile.getElement() && tmp.getPoint().getX() == tile.getPoint().getX()
					&& tmp.getPoint().getY() == tile.getPoint().getY())
				return true;
		}
		return false;
	}

	public void setPaintColor(Color color) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (above) {
			x -= ((Editor.scrollPane.getWidth() - P_WIDTH) / 2);
			y -= ((Editor.scrollPane.getHeight() - P_HEIGHT) / 2);
		}
		Point p = clickToGrid(x, y);
		tiledisegnare = new Tile(p, paintImage, currentElement, size);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
