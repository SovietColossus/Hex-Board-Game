package edu.indiana.cs.c212.gameMechanics.galexeev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;
import edu.indiana.cs.c212.players.Player;

public class TournamentPlayer implements Player {


	private Boolean isFirstMove;
	private Move move;
	protected PlayerColor color;
	
	public TournamentPlayer(PlayerColor c) {
		isFirstMove = true;
		this.color = c;
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		int boardSize = board.getSize();
		
		// So red and blue have literally the same algorithms,
		// just made it so one is vertical, one is horizontal
		
		//Just realized a problem, that it just keeps going North if the path is fully blocked ahead of time...
		//I may need to add another if statment where what would happen is that instead of going North, it'd go south,
		//and it would check if it would be free in the future, then it'd decide which one is better.
		//I'll explain graphically in person, because that is better.
		//It has a 90% win rate, but that may be a grand major problem as it is only adjusting its path in one direction
		//I will fix that by next Tuesday
		
		ArrayList<Move> possibleMoves = new ArrayList<Move>(); //List of all possible moves for moving closer towards goal
		
		if (color.equals(PlayerColor.BLUE)) {
			if (isFirstMove == true) {
				isFirstMove = false;
				if (legalMoves.contains(new Move(0, boardSize-1))) { //Default is start in the middle.
					this.move = new Move(0, boardSize / 2 );
					return move;
				} else {
					this.move = new Move(0, boardSize - 2); //If Default is taken, since this is blue move, default can be one tile north
					return move;
				}
			} else { 
				Set<Tile> newNeighbors = board.getNeighbors(board.getTileAt(
						(this.move.getX()), (this.move.getY())));
				System.out.println(newNeighbors.size());
				// Check for moves moving further

				if (possibleMoves.isEmpty()) {
					for (int i = 0; i < legalMoves.size(); i++) {
						Move currentMove = legalMoves.get(i);
						if (newNeighbors.contains(board.getTileAt(
								currentMove.getX(), currentMove.getY()))) {
							// System.out.println("scanning y-down neighbors...");
							if (currentMove.getX() > move.getX()) {
								possibleMoves.add(currentMove);
							}
						}
					}
				}
				if (possibleMoves.isEmpty()) { 
					for (Iterator<Move> moveIt = legalMoves.iterator(); moveIt //issue is that it only goes up
							.hasNext();) {
						Move currentMove = moveIt.next();
						if (newNeighbors.contains(board.getTileAt(
								currentMove.getX(), currentMove.getY()))) {
							if (currentMove.getX() == move.getX()) {
								possibleMoves.add(currentMove);
							}
						}
					}
				}
				for (int i = 0; i < possibleMoves.size(); i++) {
					Move currentMove = possibleMoves.get(i);
					if (((currentMove.getY())) == move.getY()) {
						move = currentMove;
					}
				}
				// if even at this point, there are no moves, make a random
				// legal move
				if (possibleMoves.isEmpty()) {
					if (legalMoves.isEmpty()) {
						move = null;
					} else {
						move = legalMoves.get(0);
					}
				}
				if (!(board.getTileAt(((move).getX()), (move.getY()))
						.getColor().equals(PlayerColor.BLANK))) {
					if (possibleMoves.isEmpty()) {
						move = legalMoves.get(0); 
					} else {
						move = possibleMoves.get(0); 
					}
				}
				System.out.println("Blue AI move " + move.getX() + ", " //Blue move SysOut//
						+ move.getY());
				return move; //returns the move for the player to make
			}
		}
		else if (color.equals(PlayerColor.RED)) {
			if (isFirstMove == true) {
				isFirstMove = false;
				this.move = new Move((int) Math.floor(boardSize / 2), 0); //First move is always half way along the board basically. Lol
				return move;
			} else {
				Set<Tile> newNeighbors = board.getNeighbors(board.getTileAt((this.move.getX()), (this.move.getY()))); //All the neighbors.
				//System.out.println(newNeighbors.size()); //prints out all the neighbors next to the last move made				
							
				if (possibleMoves.isEmpty()) {
					for (int i = 0; i < legalMoves.size(); i++) { //checks the ahead tiles are legal
						Move currentMove = legalMoves.get(i);
						if (newNeighbors.contains(board.getTileAt(
								currentMove.getX(), currentMove.getY()))) {
							if (currentMove.getY() > move.getY()) {
								possibleMoves.add(currentMove); //adds to possible moves that it will make
							}
						}
					}
				}
				if (possibleMoves.isEmpty()) { // if no moves can go further, go horizontally in only one direction
					for (Iterator<Move> moveIt = legalMoves.iterator(); moveIt //this is the issue at hand, it only goes right.
							.hasNext();) { //yay iteration
						Move currentMove = moveIt.next();
						if (currentMove.getY() == move.getY()) {
							possibleMoves.add(currentMove);
						}
					}
				}
				for (int i = 0; i < possibleMoves.size(); i++) {
					Move currentMove = possibleMoves.get(i);
					if (((currentMove.getX())) == move.getX()) {
						move = currentMove;
					}
				}
				if (!(board.getTileAt(((move).getX()), (move.getY()))
						.getColor().equals(PlayerColor.BLANK))) { //Makes random move basically
					if (possibleMoves.isEmpty()) {
						move = legalMoves.get(0);
					} else {
						move = possibleMoves.get(0);
					}
				}
				System.out.println("Red AI move: " + move.getX() + ", " 
						+ move.getY()); //Red move SysOut//
				return move;

			}
		} else if (legalMoves.isEmpty()) {
			return null; //returns null in the case of the board being empty
		} else {
			if (legalMoves.size() == 1)
				return legalMoves.get(1); //If there is only one legal move left, it'll take that move.
		}
		return null;
	}

	@Override
	public String getName() {
		return "Player AI"; //self explanatory
	}

	@Override
	public PlayerColor getColor() {
		return this.color;
	}
}