package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

@SuppressWarnings("serial")
public class TurnViewer extends JPanel implements Observer {
	private PlayerColor playercolor;
	private GameRunner currentgame;
	private Polygon triangle;
	

	public TurnViewer(PlayerColor player, GameRunner game){
		
		
		int width = 150;
		int height = 50;
		Dimension hello = new Dimension(width,height);
		setPreferredSize(hello);
		
		
		
		this.currentgame = game;
		this.playercolor = player;
		currentgame.addObserver(this);
		
		int[] xPoints = {0,0,30};
        int[] yPoints = {0,30,30};
        this.triangle = new Polygon(xPoints,yPoints,3);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	
	public Color getPlayerColor(PlayerColor player){
		if(player == PlayerColor.RED){
			return Color.RED;
		} else
			return Color.BLUE;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawPolygon(triangle);
		
		if(currentgame.getCurrentPlayer().getColor().equals(playercolor)){
			g.setColor(getPlayerColor(playercolor));
			//g.fillPolygon(triangle);
		}
		
		else{
			g.setColor(Color.BLACK);
			//g.fillPolygon(triangle);
		}
		
		g.fillPolygon(triangle);
		
	}

}
