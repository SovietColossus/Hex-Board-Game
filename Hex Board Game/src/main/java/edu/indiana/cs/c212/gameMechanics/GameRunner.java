package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.CommandLinePlayer;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.players.PlayerAI;
import edu.indiana.cs.c212.players.PointAndClickPlayer;
import edu.indiana.cs.c212.players.SimpleRandom;
import edu.indiana.cs.c212.view.graphical.GraphicalBoardView;
import edu.indiana.cs.c212.view.textual.CommandLineView;

public class GameRunner extends Observable implements Runnable {

	private SimpleGameBoard board;
	private Player PlayerRed, PlayerBlue;
	private Rules rules;
	private int boardSize;
	private boolean running;

	public GameRunner(int boardSize, String red, String blue, String ruleSet) {
		// String red is actually a PlayerType, specifying the type of player
		// that will ultimately be red.
		this.boardSize = boardSize;
		this.board = new SimpleGameBoard(boardSize);
		this.PlayerBlue = createPlayer(blue, PlayerColor.BLUE);
		this.PlayerRed = createPlayer(red, PlayerColor.RED);
		this.rules = createRules(ruleSet, board, PlayerRed, PlayerBlue);
		this.running = true;

	}

	public static List<String> getPlayersList() {
		List<String> playersList = new ArrayList<String>();
		playersList.add("Point And Click Player");
		playersList.add("Player AI");
		playersList.add("Command Line Player");
		playersList.add("Simple Random");
		return playersList;

	}

	public Board getBoard() {
		return board;
	}

	@Override
	public void run() {


		while (running) {

			Move move = getCurrentPlayer().getMove(new SimpleGameBoard(
					(SimpleGameBoard) getBoard()),
					rules.getLegalMoves(getCurrentPlayer()));

			//System.out.println(rules.getPlayers().peek().getName() + ":" + move);


			try {
				rules.makeMove(move);
			} catch (InvalidMoveException e) {
				//System.out.println();
				System.out.println("\nYou cheated!");
				System.out.println("\nTry another move.");
				continue;
			}

			if (rules.checkForWins() != null) {
				System.out.print(rules.checkForWins());
				System.out.print(" WINS!!");

				notifyObservers();

				stopGame();
			} else {
				rules.nextTurn();

				setChanged();
				notifyObservers();
			}
		}
	}

	public void stopGame() {
		running = false;
	}

	protected static Rules createRules(String ruleSet, Board board, Player red,
			Player blue) {
		switch (ruleSet) {

		case "Lose By Connecting Rules":
			LoseByConnectingRules connectingRules = new LoseByConnectingRules(
					board, red, blue);
			return connectingRules;
		case "Standard Rules":
			StandardRules standardRules = new StandardRules(board, red, blue);
			return standardRules;
		case "OverWrite Rules":
			OverwriteRules overWriteRules = new OverwriteRules(board, red, blue);
			return overWriteRules;
		case "Random Move Rules":
			RandomMoveRules randomRules = new RandomMoveRules(board,red,blue);
			return randomRules;
		case "Remove The Move Rules":
			RemovalRules removalRules = new RemovalRules(board,red,blue);
			return removalRules;
		default:
			return null;
		}
	}

	public static List<String> getRuleSets() {
		List<String> ruleSet = new ArrayList<String>();
		ruleSet.add("Standard Rules");
		ruleSet.add("OverWrite Rules");
		ruleSet.add("Lose By Connecting Rules");
		ruleSet.add("Random Move Rules");
		ruleSet.add("Remove The Move Rules");
		return ruleSet;

	}

	protected static Player createPlayer(String playerType, PlayerColor color) {
		switch (playerType) {

		case "Point and Click":
			PointAndClickPlayer player = new PointAndClickPlayer(color);
			return player;
		case "Player AI":
			PlayerAI ai = new PlayerAI(color);
			return ai;
		case "Command Line Player":
			CommandLinePlayer commandLine = new CommandLinePlayer(color);
			return commandLine;
		case "Simple Random":
			SimpleRandom random = new SimpleRandom(color);
			return random;
		default:
			return null;
		}

	}

	public Player getCurrentPlayer() {
		return rules.getCurrentPlayer();

	}

	public static void main(String[] args) {
		if (args.equals("text")) {
			Scanner scanner = new Scanner(System.in);
			CommandLineView.setup(scanner);

		} else {
			new Thread(new GraphicalBoardView()).start();
		}
	}

}
