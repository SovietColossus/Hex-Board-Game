package edu.indiana.cs.c212.view.graphical;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerChoicePanel extends JPanel {

	private String playerType;
	private JComboBox<String> typelist;
	private JLabel label;

	public PlayerChoicePanel(String name) {
		
		int width = 150;
		int height = 50;
		Dimension hello = new Dimension(width,height);
		setPreferredSize(hello);
		
		
		
		//this.playerType = name;
		label = new JLabel(name + "-Player type: ");
		
		//Update this if I add other players.
		String[] typesOfPlayers = { "Point and Click", "Player AI", "Command Line Player",
				"Simple Random"};
		
		typelist = new JComboBox<String>(typesOfPlayers);
		typelist.setVisible(true);
		label.setVisible(true);
		add(label);
		add(typelist);
	}

	public String getPlayerType() {
		playerType = (String) typelist.getSelectedItem();
		return playerType;
	}

}
