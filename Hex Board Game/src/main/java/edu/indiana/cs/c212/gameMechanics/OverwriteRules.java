package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class OverwriteRules extends StandardRules {

	public OverwriteRules(Board board, Player red, Player blue) {
		super(board, red, blue);
	}

	public boolean isLegalMove(Move m) {
		return getLegalMoves(playersQueue.peek()).contains(m);
	}

	public ArrayList<Move> getLegalMoves(Player player) {
		ArrayList<Move> legalMoves = new ArrayList<Move>();

		for (int x = 0; x < board.getSize(); x++) {
			for (int y = 0; y < board.getSize(); y++) {
				if (!board.getTileAt(x, y).getColor().equals(PlayerColor.BLANK)
						&& board.getTileAt(x, y).getColor()
								.equals(getNextPlayer().getColor())) {
					legalMoves.add(new OverwriteMove(x, y));
				} else if (board.getTileAt(x, y).getColor()
						.equals(PlayerColor.BLANK)) {
					legalMoves.add(new Move(x, y));
				}
			}
		}
		return legalMoves;
	}

}
