package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import edu.indiana.cs.c212.board.Board;

/**
 * @author <galexeev>
 * @author <janson>
 * 
 **/

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements ActionListener, Observer {

	protected Board board;
	private int radius;
	private double b;
	private double a;
	private HexTile[][] tileArray;
	private HexTile tile;

	public BoardPanel(Board board) {
		// TODO
		this.board = board;
		//900
		// 800
		int height = 900;
		int width = 800;
		Dimension hello = new Dimension(width, height);
		this.setPreferredSize(hello);
		this.setBackground(Color.BLACK);
		// setVisible(true);

		// this prevents the board from being created and then disappearing
		this.setLayout(null);

		this.tileArray = new HexTile[board.getSize() + 2][board.getSize() + 2];

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HexTile tile = (HexTile) e.getSource();
		MoveEvent move = new MoveEvent(new Point(tile.getBoardX(),
				tile.getBoardY()), 0);
		for (AWTEventListener l : Toolkit.getDefaultToolkit()
				.getAWTEventListeners()) {
			l.eventDispatched(move);
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	// This builds the board of tiles.
	public void paintComponent(Graphics g) {

//		Toolkit toolKit = Toolkit.getDefaultToolkit();
//		radius = (int) (toolKit.getScreenSize().height / board.getSize()) / 2;

		//radius = (int) ((getHeight() / 3) / board.getSize());

		if(getHeight() > getWidth()){
			radius = (int) (getWidth() / 2.75) / board.getSize();
		} else {
			radius = (int) (getHeight() / 2.75) / board.getSize();	
		}


		// radius = 30;
		b = Math.cos(Math.PI / 6) * radius;
		a = Math.sin(Math.PI / 3) * radius;

		// mess with these see if i can make the board look better.

		// x = 50;
		int x = 50;
		// y = 35;
		int y = 35;

		for (int i = 0; i < tileArray.length; i++) {
			for (int j = 0; j < tileArray.length; j++) {
				if (tileArray[i][j] == null) {
					tile = new HexTile(x, y, radius, board.getTileAt(j - 1,
							i - 1));
					tile.setBounds(x, y, getWidth(), getHeight());
					this.add(tile);
					//tile.paint(g);
					tile.addActionListener(this);
					tileArray[i][j] = tile;
				} else {
					tile.setRadius(radius);
				}
				x += b + 1;
			}
			x = 51 + (int) (((i + 1) * b) / 2);
			y += a - 1;
		}

		super.paintComponent(g);
		repaint();

	}

//		// x = 50;
//		int x = 50;
//		// y = 35;
//		int y = 35;
//
//		for (int i = 0; i <= board.getSize() + 1; i++) {
//			for (int j = 0; j <= board.getSize() + 1; j++) {
//				HexTile tile = new HexTile(x, y, radius, board.getTileAt(j - 1,
//						i - 1));
//				tile.setBounds(x, y, getWidth(), getHeight());
//				this.add(tile);
//				tile.addActionListener(this);
//				// horizontal offset
//				x += b + 1;
//			}
//			// x = 51
//			x = 51 + (int) (((i + 1) * b) / 2);
//			y += a - 1;
//		}
//		super.paintComponent(g);
//		repaint();
//
//	}

}

/*package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.indiana.cs.c212.board.Board;

/**
 * @author <janson>
 * @author <galexeev>
 * 
 **

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements ActionListener, Observer {

	protected Board board;
	private int radius;
	private double b;
	private double a;
	private HexTile[][] tileArray;
	private HexTile tile;
	private Image img = Toolkit.getDefaultToolkit().getImage("Blue-Green-beautiful-nature-21891805-1920-1200.jpg");
	private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean rothschild;
	
	public BoardPanel(Board board) {
		// TODO
		rothschild = false;
		this.board = board;
		//900
		// 800
		
		this.setPreferredSize(screenSize);
		//this.setBackground(Color.BLACK);
		
		// setVisible(true);

		// this prevents the board from being created and then disappearing
		this.setLayout(null);
		
		this.tileArray = new HexTile[board.getSize() + 2][board.getSize() + 2];

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HexTile tile = (HexTile) e.getSource();
		MoveEvent move = new MoveEvent(new Point(tile.getBoardX(),
				tile.getBoardY()), 0);
		for (AWTEventListener l : Toolkit.getDefaultToolkit()
				.getAWTEventListeners()) {
			l.eventDispatched(move);
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

	// This builds the board of tiles.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			radius = (int) (getHeight() / 2.75) / board.getSize();	
		


		// radius = 30;
		b = Math.cos(Math.PI / 6) * radius;
		a = Math.sin(Math.PI / 3) * radius;

		// mess with these see if i can make the board look better.

		// x = 50;
		int x = 50;
		// y = 35;
		int y = 35;

		for (int i = 0; i < tileArray.length; i++) {
			for (int j = 0; j < tileArray.length; j++) {
				if (tileArray[i][j] == null) {
					tile = new HexTile(x, y, radius, board.getTileAt(j - 1,
							i - 1));
					tile.setBounds(x, y, getWidth(), getHeight());
					this.add(tile);
					//tile.paint(g);
					tile.addActionListener(this);
					tileArray[i][j] = tile;
				} else {
					tile.setRadius(radius);
				}
				x += b + 1;
			}
			x = 51 + (int) (((i + 1) * b) / 2);
			y += a - 1;
		}
	rothschild = true; 
		
	if(rothschild = false) {
		g.drawImage(img,0,0,null);
	}
	repaint();
	}

}*/