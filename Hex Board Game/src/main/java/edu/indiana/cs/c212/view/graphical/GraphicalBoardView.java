package edu.indiana.cs.c212.view.graphical;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

/**
 * @author <galexeev>
 * @author <janson>
 * 
 **/

@SuppressWarnings("serial")
public class GraphicalBoardView extends JFrame implements ActionListener,
		Runnable {

	private PlayerChoicePanel redChoicePanel = new PlayerChoicePanel("Red");
	private PlayerChoicePanel blueChoicePanel = new PlayerChoicePanel("Blue");
	private BoardSetupPanel boardSetupPanel = new BoardSetupPanel();
	private JPanel mvpPanel = new JPanel();
	private GameRunner ourGame;
	private GameRunner ourGame1;
	private JButton startButton = new JButton("Start Game");
	private RulesChoicePanel rulesPanel = new RulesChoicePanel();
	private BoardPanel boardPanel;
	private JPanel colorPanel;
	private Thread gameThread;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public GraphicalBoardView() {
		super();
		// Layout type
		BoxLayout layout = new BoxLayout(mvpPanel, BoxLayout.Y_AXIS);
		mvpPanel.setLayout(layout);

		startButton.setEnabled(true);
		startButton.setActionCommand("Start");
		startButton.addActionListener(this);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		int width = 100;
		int height = 75;
		Dimension hello = new Dimension(width, height);
		startButton.setPreferredSize(hello);

		// JFrame Detail
		setTitle("Trails");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mvpPanel);
		mvpPanel.add(redChoicePanel);
		mvpPanel.add(blueChoicePanel);
		mvpPanel.add(rulesPanel);
		mvpPanel.add(boardSetupPanel);
		mvpPanel.add(startButton);

		pack();

		// JFrame location is the middle of the screen.
		setLocationRelativeTo(null);
		//setVisible(true);
		//setResizable(true);
		 
		
	} 
	
	protected void prepareGame() {

		boolean isGameNull = (ourGame == null);

		System.out.println("I just prepared a game");
		ourGame = new GameRunner(boardSetupPanel.getBoardSize(),
				redChoicePanel.getPlayerType(),
				blueChoicePanel.getPlayerType(), rulesPanel.getRuleSet());
		setPreferredSize(screenSize);
		this.setLocation(0,0);
		if (!isGameNull) {
			//setPreferredSize(screenSize);
			boardPanel.removeAll();
			remove(boardPanel);
			remove(colorPanel);

			JLabel redTurnLabel = new JLabel("Red: ");
			TurnViewer redTurnPanel = new TurnViewer(PlayerColor.RED, ourGame);
			JLabel blueTurnLabel = new JLabel("Blue: ");
			TurnViewer blueTurnPanel = new TurnViewer(PlayerColor.BLUE, ourGame);

			System.out.println("Your game wasn't null");

			boardPanel = new BoardPanel(ourGame.getBoard());
			add(boardPanel);

			colorPanel = new JPanel();
			colorPanel.setLayout(new FlowLayout());
			colorPanel.add(redTurnLabel);
			colorPanel.add(redTurnPanel);
			colorPanel.add(blueTurnLabel);
			colorPanel.add(blueTurnPanel);

			mvpPanel.add(colorPanel);
			colorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			//this.setLocation(0,0);
			
		}

		if (isGameNull) {
			//setPreferredSize(screenSize);
			JLabel redTurnLabel = new JLabel("Red: ");
			TurnViewer redTurnPanel = new TurnViewer(PlayerColor.RED, ourGame);
			JLabel blueTurnLabel = new JLabel("Blue: ");
			TurnViewer blueTurnPanel = new TurnViewer(PlayerColor.BLUE, ourGame);

			System.out.println("Your game was null so I made one");

			boardPanel = new BoardPanel(ourGame.getBoard());
			add(boardPanel);

			colorPanel = new JPanel();
			colorPanel.setLayout(new FlowLayout());
			colorPanel.add(redTurnLabel);
			colorPanel.add(redTurnPanel);
			colorPanel.add(blueTurnLabel);
			colorPanel.add(blueTurnPanel);

			mvpPanel.add(colorPanel);
			colorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			pack();
			//this.setLocation(0,0);
			
			
			}

	}

	protected void createAndShowGui() {
		setVisible(true);
	}

	@Override
	public void run() {
		createAndShowGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Dummy thread creation that takes updated values of gameThread//
		Thread gameThread111 = new Thread();
		if (e.getActionCommand() == "Start") {
			//checks if game is null or not//
			if (ourGame == null) {
				//if it's null, prepare the game, create a new thread. THEN, game1 will take gameThread's values, and will be
				//the thread to be run. We start gameThread111 as the dummy thread. Kinda like the concept of hard copy vs soft copy
				//you don't want to copy over the file, you want to copy over the copy of the file for safety purposes. Make sense?
				//Then, we revalidate and repaint. Still don't know if it matters where it is, but it works for me here.
				prepareGame();
				gameThread = new Thread(ourGame);
				gameThread111 = gameThread;
				gameThread111.start();
				revalidate();
				repaint();
			} else {
				//if game is Notnull, we interrupt the game1 thread and stop the game of gameRunner (for stopping everything basically).
				//Then we prepare a new game, reference the new set of values given by the new ourGame, and set ourGame1 to those values.
				//then we make a thread with ourGame1 as the input, set the values of gameThread to gameThread111, start that thread,
				//revalidate, and repaint. Woohoo!
				gameThread111.interrupt();
				ourGame.stopGame();
				prepareGame();
				ourGame1 = ourGame;
				gameThread = new Thread(ourGame1);
				gameThread111 = gameThread;
				gameThread111.start();
				revalidate();
				repaint();
			}
		}
		
	
	}
}