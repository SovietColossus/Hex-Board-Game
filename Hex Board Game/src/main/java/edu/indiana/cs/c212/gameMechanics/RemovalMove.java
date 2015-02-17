package edu.indiana.cs.c212.gameMechanics;

public class RemovalMove extends Move {
	private int x,y;

	public RemovalMove(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "REMOVE " + "Move [x=" + x + ", y=" + y + "]";
		
	}
}
