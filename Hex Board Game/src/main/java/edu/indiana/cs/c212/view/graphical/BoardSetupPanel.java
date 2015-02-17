package edu.indiana.cs.c212.view.graphical;

import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class BoardSetupPanel extends JPanel {

	public static final int MIN_SUPPORTED_BOARD_SIZE = 3;
	public static final int MAX_SUPPORTED_BOARD_SIZE = 99;
	public static final int DEFAULT_BOARD_SIZE = 7;
	public static int step = 1;
	private JSpinner boardSizeSpinner;
	private JLabel boardlabel;

	public BoardSetupPanel() {
		
		
		int width = 200;
		int height = 50;
		Dimension hello = new Dimension(width,height);
		setPreferredSize(hello);

		boardlabel = new JLabel("Board Size: ");
		SpinnerNumberModel choices = new SpinnerNumberModel(DEFAULT_BOARD_SIZE,
				MIN_SUPPORTED_BOARD_SIZE, MAX_SUPPORTED_BOARD_SIZE, step);

		boardSizeSpinner = new JSpinner(choices);

		boardSizeSpinner.setVisible(true);
		boardlabel.setVisible(true);

		add(boardlabel);
		add(boardSizeSpinner);
	}

	public int getBoardSize() {
		int value = (Integer) boardSizeSpinner.getValue();
		return value;
	}

}
