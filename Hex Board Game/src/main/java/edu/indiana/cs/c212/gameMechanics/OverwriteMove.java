package edu.indiana.cs.c212.gameMechanics;


public class OverwriteMove extends Move {
	private int x,y;

	public OverwriteMove(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "OVERWRITE " + "Move [x=" + x + ", y=" + y + "]";
		
	}

}
