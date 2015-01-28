package com.pacman.model;

public class Pacman extends GameElement {
	private State state;
	
	public Pacman(float x, float y) {
		super(x, y);
		state = State.RIGHT;
	}
	
	public void setPositionX(float x){
		this.posX = x;
	}
	
	public void setPositionY(float y){
		this.posY = y;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State s){
		state = s;
	}
}
