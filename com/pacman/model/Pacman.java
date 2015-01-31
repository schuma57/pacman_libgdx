package com.pacman.model;

public class Pacman extends MoveableElement {
	private State state;
	
	public Pacman(float x, float y) {
		super(x, y);
		state = State.RIGHT;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State s){
		state = s;
	}
}
