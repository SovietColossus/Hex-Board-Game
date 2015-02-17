package edu.indiana.cs.c212.players;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class PlayerAI extends AbstractPlayer {

	private PlayerColor color;
	private String name;
	private Boolean first;
	private Tile current;
	private int boardsize;

	public PlayerAI(PlayerColor c) {
		super(c);
		this.color = c;
		this.name = "Player AI";
		this.first = true;
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		int boardsize = board.getSize();
		this.boardsize = boardsize;

		if (first == true) {
			if (this.color == PlayerColor.RED) {
				this.first = false;
				current = new Tile(color, new Point(
						boardsize -1, 0));
				return new Move(boardsize -1, 0);
			} else if (this.color == PlayerColor.BLUE){
				if (board.getTileAt(0, boardsize -1)
						.getColor() == PlayerColor.BLANK) {
					first = false;
					current = new Tile(color, new Point(0,
							boardsize -1));
					return new Move(0, boardsize -1);
				} else {
					first = false;
					current = new Tile(color, new Point(0,
							boardsize - 2));
					return new Move(0, boardsize - 2);
				}
			}
		} else {
			Move move = getBest(board, current);
			current = new Tile(color, new Point(move.getX(), move.getY()));
			System.out.println(move);
			return move;
		}
		return null;
	}

	public Move getBest(Board b, Tile current) {
		Set<Tile> legal = b.getNeighbors(current);
		// getTileAt
		int cx = (int) current.getPoint().getX();
		int cy = (int) current.getPoint().getY();

		if (color == PlayerColor.RED) {
			if ( b.getTileAt(cx, cy + 1).getColor()
					.equals(PlayerColor.BLANK)) {
		return new Move(cx, cy + 1);
			} else 	if (b.getTileAt(cx - 1, cy + 1).getColor()
							.equals(PlayerColor.BLANK)) { 
				return new Move(cx - 1, cy + 1);
			} else if(b.getTileAt(cx, cy).getColor()
					.equals(PlayerColor.BLANK)){
				return new Move(cx, cy);
			} else if (b.getTileAt(cx + 1, cy).getColor()
							.equals(PlayerColor.BLANK)) {
				return new Move(cx + 1, cy);
			} else if (b.getTileAt(cx + 1, cy - 1).getColor()
							.equals(PlayerColor.BLANK)) {
				return new Move(cx + 1, cy - 1);
			} else if (legal.contains(b.getTileAt(cx - 1, cy))) {
				return new Move(cx - 1, cy);
			} else {
				int y = 0;
				while (true) {
					int x = 1;
					while (true) {
						if (x == b.getSize()) {
							break;
						}
						if (b.getTileAt(x, y).getColor().equals(PlayerColor.BLANK)) {
							return new Move(x, y);
						}
						x += 1;
					}
					y += 1;
				}
			}
		} else {

			if (legal.contains(new Tile(PlayerColor.BLANK, new Point(current
					.getPoint().x + 1, cy)))) {
				return new Move(cx + 1, cy);
			} else if (legal.contains(new Tile(PlayerColor.BLANK, new Point(cx,
					cy + 1)))) {
				return new Move(cx, cy + 1);
			} else if (legal.contains(new Tile(PlayerColor.BLANK, new Point(
					cx - 1, cy + 1)))) {
				return new Move(cx + 1, cy - 1);
			} else if (legal.contains(new Tile(PlayerColor.BLANK, new Point(
					cx + 1, cy - 1)))) {
				return new Move(cx + 1, cy - 1);
			} else if (legal.contains(new Tile(PlayerColor.BLANK, new Point(cx,
					cy - 1)))) {
				return new Move(cx, cy - 1);
			} else {
				int x = 0;
				while (true) {
					int y = 1;
					while (true) {
						if (y == b.getSize()) {
							break;
						}
						if (b.getTileAt(x, y).getColor() == PlayerColor.BLANK) {
							return new Move(x, y);
						}
						y += 1;
					}
					x += 1;
				}
			}
		}
		// Score board function perhaps? That's an extension.

	}

	@Override
	public String getName() {
		return name;
	}
}