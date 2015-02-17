package edu.indiana.cs.c212.gameMechanics;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.players.Player;

public class LoseByConnectingRules extends StandardRules {
	
	public LoseByConnectingRules(Board board, Player red, Player blue) {
		super(board, red, blue);
	}
	
	public PlayerColor checkForWins(){
		if(didBlueWin()){
			return PlayerColor.RED;
		}
		else if (didRedWin()){
			return PlayerColor.BLUE;
		}
		else return null;
	}
}
