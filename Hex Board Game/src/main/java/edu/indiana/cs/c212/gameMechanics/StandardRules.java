package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

/*
 @author galexeev
 @author janson

 */

public class StandardRules implements Rules {

	protected final Board board;
	private Player red;
	private Player blue;
	protected LinkedList<Player> playersQueue = new LinkedList<Player>();

	public StandardRules(Board board, Player red, Player blue) {
		this.board = board;
		this.red = red;
		this.blue = blue;
		playersQueue.add(red);
		playersQueue.add(blue);
	}

	public static boolean areTilesConnected(Board board, Tile start, Tile goal,
			PlayerColor color) {
		Queue<Tile> possiblegoals = new LinkedList<Tile>();
		Set<Tile> checkedtiles = new HashSet<Tile>();

		possiblegoals.add(start);
		checkedtiles.add(start);

		while (!possiblegoals.isEmpty()) {
			Tile current_tile = possiblegoals.poll();
			checkedtiles.add(current_tile);

			if (current_tile.equals(goal)) {
				return true;
			}

			for (Tile attachedtile : board.getNeighbors(current_tile)) {
				if (!checkedtiles.contains(attachedtile)
						&& attachedtile.getColor().equals(color)) {
					checkedtiles.add(attachedtile);
					possiblegoals.add(attachedtile);

				}
			}
		}
		return false;
	}

	@Override
	public Queue<Player> getPlayers() {
		return playersQueue;
	}

	@Override
	public PlayerColor checkForWins() {
		if (didBlueWin()) {
			return PlayerColor.BLUE;
		} else if (didRedWin()) {
			return PlayerColor.RED;
		} else
			return null;
	}

	public boolean didRedWin() {
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				if (areTilesConnected(board, board.getTileAt(i, -1),
						board.getTileAt(j, board.getSize()),
						PlayerColor.RED)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean didBlueWin() {
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				if (areTilesConnected(board, board.getTileAt(-1, i),
						board.getTileAt(board.getSize(), j),
						PlayerColor.BLUE)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Player nextTurn() {
		playersQueue.add(playersQueue.peek());
		playersQueue.remove();
		return playersQueue.peek();
	}

	@Override
	public Player getNextPlayer() {
		return playersQueue.get(1);
	}
	
	public Player getCurrentPlayer() {
		return playersQueue.get(0);
	}

	@Override
	public void makeMove(Move m) throws InvalidMoveException {
		if (isLegalMove(m)) {
			board.makeMove(m, playersQueue.get(0).getColor());
		}

		else {

			if (m.getX() < 0 || m.getY() < 0 || m.getX() > board.getSize()
					|| m.getY() > board.getSize()) {
				throw new InvalidMoveException("Outside of the board", m,
						InvalidMoveException.OUTSIDE_BOARD);
			}

			else {
				throw new InvalidMoveException("Move already taken", m,
						InvalidMoveException.ALREADY_TAKEN);
			}
		}
	}

	@Override
	public boolean isLegalMove(Move m) {
		int x = m.getX();
		int y = m.getY();
		if (x < board.getSize() && y < board.getSize() && x > -1 && y > -1) {
			if (board.getTileAt(x, y).getColor().equals(PlayerColor.BLANK)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Move> getLegalMoves(Player player) {
		ArrayList<Move> legalMovesList = new ArrayList<Move>();
		for (int x = 0; x < board.getSize(); x++) {
			for (int y = 0; y < board.getSize(); y++) {
				Move move = new Move(x, y);
				if (isLegalMove(move)) {
					legalMovesList.add(move);
				}
			}
		}
		return legalMovesList;
	}

}