package edu.indiana.cs.c212.view.graphical;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RulesChoicePanel extends JPanel {

	private String ruleSet;
	private JComboBox<String> ruleList;
	private JLabel ruleLabel;

	public RulesChoicePanel() {
		
		
		int width = 250;
		int height = 50;
		Dimension hello = new Dimension(width,height);
		setPreferredSize(hello);

		ruleLabel = new JLabel("Rule Set: ");
		String[] typesOfPlayers = { "Standard Rules",
				"Lose By Connecting Rules", "OverWrite Rules", "Random Move Rules", "Remove The Move Rules"};

		ruleList = new JComboBox<String>(typesOfPlayers);
		ruleList.setVisible(true);
		ruleLabel.setVisible(true);
		add(ruleLabel);
		add(ruleList);

	}

	public String getRuleSet() {
		ruleSet = (String) ruleList.getSelectedItem();
		return ruleSet;
	}

}
