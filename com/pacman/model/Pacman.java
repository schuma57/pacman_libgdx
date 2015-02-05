package com.pacman.model;

public class Pacman extends MoveableElement {
	
	public Pacman(float x, float y) {
		super(x, y);
		setState(State.FRONT);
		setSpeed(0.125f);
	}
	
}
