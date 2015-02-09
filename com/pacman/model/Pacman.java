package com.pacman.model;

public class Pacman extends MoveableElement {
	
	public Pacman(float x, float y) {
		super(x, y);
		setState(State.RIGHT);
		setLastState(State.RIGHT);
		setSpeed(0.125f);
	}
	
}
