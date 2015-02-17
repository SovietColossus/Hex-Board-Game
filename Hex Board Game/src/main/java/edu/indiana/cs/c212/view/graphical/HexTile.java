package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

/**
 * @author <galexeev>
 * @author <janson>
 **/

@SuppressWarnings({ "serial" })
public class HexTile extends JButton {

	private int radius, x, y;
	private Polygon hex;
	private double pi = Math.PI;
	private Tile tile;

	public HexTile(int x, int y, int radius, Tile tile) {
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.tile = tile;
		hex = new Polygon();
		setVisible(true);

		for (int i = 0; i < 6; i++) {
			hex.addPoint((int) (x + this.radius * Math.sin(i * 2 * pi / 6)),
					(int) (y + this.radius * Math.cos(i * 2 * pi / 6)));
		}
	}

	public final int getBoardX() {
		return tile.getPoint().x;

	}

	public final int getBoardY() {
		return tile.getPoint().y;
	}

	public boolean contains(Point p) {
		return hex.contains(p);
	}

	public boolean contains(int x, int y) {
		return hex.contains(x, y);
	}

	public void paint(Graphics g) {

		if (tile.getColor().equals(PlayerColor.RED)) {
			g.setColor(new Color(240, 60, 60));
		} else if (tile.getColor().equals(PlayerColor.BLUE)) {
			g.setColor(new Color(60, 60, 240));

		} else
			g.setColor(new Color(200,200,200));

		g.fillPolygon(hex);
		g.setColor(Color.BLACK);
		g.drawPolygon(hex);

	}

	public void processMouseEvent(MouseEvent e) {
		if (hex.contains(e.getPoint())) {
			super.processMouseEvent(e);
		}
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	// This is for testing what the hex is.
	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(500, 500);
	// frame.setLocationRelativeTo(null);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.getContentPane().add(
	// new HexTile(0, 0, 70, new Tile(PlayerColor.RED,
	// (new Point(0, 0)))));
	// frame.setVisible(true);
	// }

}