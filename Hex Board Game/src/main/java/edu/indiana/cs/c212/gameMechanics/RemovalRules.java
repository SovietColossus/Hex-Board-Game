package edu.indiana.cs.c212.gameMechanics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class RemovalRules extends StandardRules {
	
	//protected LinkedList<Player> playersDope = new LinkedList<Player>();
	
	public RemovalRules(Board board, Player red, Player blue) {
		super(board, red, blue);
		
		// TODO Auto-generated constructor stub
	}

	public boolean isLegalMove(Move m) {
		return getLegalMoves(playersQueue.peek()).contains(m);
	}
	
	public Queue<Player> getPlayers() {
		return playersQueue;
	}

	public ArrayList<Move> getLegalMoves(Player player) {
		ArrayList<Move> legalMoves = new ArrayList<Move>();

		for (int x = 0; x < board.getSize(); x++) {
			for (int y = 0; y < board.getSize(); y++) {
				if (!board.getTileAt(x, y).getColor().equals(PlayerColor.BLANK)) {
					legalMoves.add(new RemovalMove(x, y));
				} else if (board.getTileAt(x, y).getColor()
						.equals(PlayerColor.BLANK)) {
					legalMoves.add(new Move(x, y));
				}
			}
		}
		return legalMoves;
	}

	public void makeMove(Move m) throws InvalidMoveException {
		if (isLegalMove(m) && !board.getTileAt(m.getX(), m.getY()).getColor().equals(PlayerColor.BLANK)) {
			board.makeMove(m, PlayerColor.BLANK);
		}
		else if (isLegalMove(m) && board.getTileAt(m.getX(), m.getY()).getColor().equals(PlayerColor.BLANK)) {
			board.makeMove(m, getCurrentPlayer().getColor());
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
}
