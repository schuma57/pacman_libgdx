package com.pacman.model;

public class Pacman extends MoveableElement {
	
	public Pacman(float x, float y, World w) {
		super(x, y, w);
		setState(State.LEFT);
		//setLastState(State.LEFT);
		setSpeed(0.5f);
	}
	
}
