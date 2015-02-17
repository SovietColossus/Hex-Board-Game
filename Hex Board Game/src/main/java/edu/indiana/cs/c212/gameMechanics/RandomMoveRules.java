package edu.indiana.cs.c212.gameMechanics;

import java.util.LinkedList;
import java.util.Queue;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class RandomMoveRules extends StandardRules {
	
	private Player red;
	private Player blue;
	private Player currentPlayer;
	
	public RandomMoveRules(Board board, Player red, Player blue) {
		super(board, red, blue);
		this.red = red;
		this.blue = blue;
		playersQueue.add(nextTurn());
		playersQueue.add(nextTurn());
	}

	public Player nextTurn() {
		double shawty = Math.random();
		if(shawty >= 0.5) {
			playersQueue.remove();
			currentPlayer = red;
			playersQueue.add(currentPlayer);
			return red;
		} else {
			playersQueue.remove();
			currentPlayer = blue;
			playersQueue.add(currentPlayer);
			return blue;
		}
		
	}
}
