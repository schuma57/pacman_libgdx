package com.pacman.model;

public abstract class Ghost extends MoveableElement{

	public Ghost(float x, float y) {
		super(x, y);
		setState(State.UP);
		setLastState(State.UP);
	}
	
	public abstract void ghostMove();
}
