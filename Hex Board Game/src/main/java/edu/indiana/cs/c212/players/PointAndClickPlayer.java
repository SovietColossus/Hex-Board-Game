package edu.indiana.cs.c212.players;

import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.util.List;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.OverwriteMove;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;
import edu.indiana.cs.c212.gameMechanics.RemovalMove;

/**
 * @author <galexeev>
 * @author <janson>
 * 
 **/

public class PointAndClickPlayer extends AbstractPlayer implements
		AWTEventListener {

	private String playerName;
	private Point click;

	public PointAndClickPlayer(PlayerColor c) {
		super(c);
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		Toolkit.getDefaultToolkit().addAWTEventListener(this, 0);

		while (click == null) {

			try {
				Thread.sleep(5);

			} catch (InterruptedException error) {

				error.printStackTrace();
			}
		}

		int x = click.x, y = click.y;
		click = null;
		
		Move move = new Move(x, y);
		OverwriteMove overwrite = new OverwriteMove(x,y);
		RemovalMove remove = new RemovalMove(x,y);
		if(legalMoves.contains(move)){

			Toolkit.getDefaultToolkit().removeAWTEventListener(this);
			

			return move;
			
		} if (legalMoves.contains(remove)) {
			return remove;
		}
		
		else {
			Toolkit.getDefaultToolkit().removeAWTEventListener(this);
			
			
			return overwrite;
		}
		/*
		 * 	public Move getMove(Board board, List<Move> legalMoves) {
		Toolkit.getDefaultToolkit().addAWTEventListener(this, 0);

		while (click == null) {

			try {
				Thread.sleep(5);

			} catch (InterruptedException error) {

				error.printStackTrace();
			}
		}

		int x = click.x, y = click.y;

		Move move = new Move(x, y);

		if (legalMoves.contains(move)) {

			Toolkit.getDefaultToolkit().removeAWTEventListener(this);
			click = null;

			return move;

		} else {
			if (this.getColor().equals(PlayerColor.BLUE)) {
				if (board.getTileAt(x, y).getColor().equals(PlayerColor.RED)) {
					Toolkit.getDefaultToolkit().removeAWTEventListener(this);
					click = null;

					OverwriteMove overwrite = new OverwriteMove(x, y);
					return overwrite;
				} else {
					Toolkit.getDefaultToolkit().removeAWTEventListener(this);
					click = null;

					RemovalMove remove = new RemovalMove(x, y);
					return remove;
				}
			} else if (this.getColor().equals(PlayerColor.RED)) {
				if (board.getTileAt(x, y).getColor().equals(PlayerColor.BLUE)) {
					Toolkit.getDefaultToolkit().removeAWTEventListener(this);
					click = null;

					OverwriteMove overwrite = new OverwriteMove(x, y);
					return overwrite;
				} else {
					Toolkit.getDefaultToolkit().removeAWTEventListener(this);
					click = null;

					RemovalMove remove = new RemovalMove(x, y);
					return remove;
				}
			}
		}
		return move;

		 */

	}

	@Override
	public String getName() {
		playerName = "Point and Click";
		return playerName;
	}

	@Override
	public void eventDispatched(AWTEvent event) {
		// TODO Auto-generated method stub
		click = (Point) event.getSource();

		// Thread.currentThread().interrupt();

	}

}