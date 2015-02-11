package com.pacman.model;

public class Pacman extends MoveableElement {
	
	public Pacman(int x, int y, World w) {
		super(x, y, w);
		setState(State.LEFT);
		//setLastState(State.LEFT);
		setSpeed(0.5f);
	}
	
}
