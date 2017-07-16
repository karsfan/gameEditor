package com.levels.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.levels.editor.ToolsPanel.Element;

public class Editor extends JFrame implements KeyEventDispatcher {

	private static final long serialVersionUID = 1L;

	public static JFileChooser fc;
	public static JScrollPane scrollPane;
	public static PreviewPanel panel;
	public ToolsPanel tools;
	public Container contentPane;
	public JMenuBar menuBar;
	public JMenu file;
	public KeyboardFocusManager keyboardManager;

	public Editor() {
		super();

		keyboardManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyboardManager.addKeyEventDispatcher(this);

		fc = new JFileChooser();
		panel = new PreviewPanel();
		setPreferredSize(new Dimension(1200, 700));
		setMinimumSize(new Dimension(1080, 700));
		tools = new ToolsPanel(panel);
		contentPane = getContentPane();
		menuBar = new JMenuBar();

		scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		setJMenuBar(menuBar);
		file = new JMenu("File");
		menuBar.add(file);

		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);

		tools.setPreferredSize(new Dimension(300, 700));
		contentPane.setLayout(new BorderLayout());

		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(tools, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);

	}

	static Action Open = new AbstractAction("Open a file..") { // menu apri
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				openFile(fc.getSelectedFile().getAbsolutePath());
		}
	};

	static Action Save = new AbstractAction("Save a file..") { // menu salva

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	};

	static Action Exit = new AbstractAction("Exit") { // menu esci

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};

	@SuppressWarnings("resource")
	public static void openFile(String fileName) {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			PreviewPanel.points.clear();
			try {
				Scanner input = new Scanner(System.in);
				input = new Scanner(fr);
				String line = input.nextLine();
				String[] split = line.split(" ");
				EditorConfig.WIDTH = Integer.parseInt(split[0]);
				EditorConfig.HEIGHT = Integer.parseInt(split[1]);
				while (input.hasNextLine()) {
					line = input.nextLine();

					String[] splittata = line.split(" ");

					for (int i = 0; i < splittata.length - 1; i++)
						PreviewPanel.addTile(splittata[i], getXY(splittata[i + 1]));
				}
				input.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			fr.close();
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveFile() {
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());

				// Salvataggio del vettore delle posizioni
				fw.write(EditorConfig.WIDTH + " " + EditorConfig.HEIGHT + "\r\n");
				Iterator<Tile> it = (Iterator<Tile>) PreviewPanel.points.iterator();
				while (it.hasNext()) {
					Tile cp = (Tile) it.next();
					if (cp.getElement() == Element.HOME)
						fw.write("HOME " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.CASTLE)
						fw.write("CASTLE " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.GROUND)
						fw.write("GROUND " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.FLOOR)
						fw.write("FLOOR " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.FLOOR2)
						fw.write("FLOOR2 " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.FLOOR3)
						fw.write("FLOOR3 " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.TREE)
						fw.write("TREE " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.WATER)
						fw.write("WATER " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.ROCK)
						fw.write("ROCK " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.LAMP)
						fw.write("LAMP " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.TEMPLE)
						fw.write("TEMPLE " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.ROAD)
						fw.write("ROAD " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.FOREST1)
						fw.write("FOREST1 " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.FOREST2)
						fw.write("FOREST2 " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.STRAW)
						fw.write("STRAW " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.PREENEMYHOME)
						fw.write("PREENEMYHOME " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
					if (cp.getElement() == Element.SHOP)
						fw.write("SHOP " + "X" + (int) cp.getPoint().getX() + "Y" + (int) cp.getPoint().getY()
								+ ";\r\n");
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Point getXY(String line) {
		int x = 0;
		int i = 1;
		for (; line.charAt(i) != 'Y'; i++) {
			x = x * 10 + Character.getNumericValue(line.charAt(i));
		}

		int y = 0;
		for (i = i + 1; line.charAt(i) != ';'; i++) {
			y = y * 10 + Character.getNumericValue(line.charAt(i));
		}
		return new Point(x, y);
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
			saveFile();
			return true;
		}
		if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0))
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				openFile(fc.getSelectedFile().getAbsolutePath());
				return true;
			}
	
		if (panel.points.size() > 0)
			if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				panel.points.remove(panel.points.size() - 1);
				panel.repaint();
				return true;
			}

		if ((e.getKeyCode() == KeyEvent.VK_C)) {
			PreviewPanel.points.clear();
			return true;
		}

		return false;
	}

}