package com.pacman.model;

public abstract class MoveableElement extends GameElement{
	private State state;
	private float speed;
	
	public MoveableElement(float x, float y) {
		super(x, y);
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
	
	public float getSpeed(){
		return speed;
	}
	
	public void setSpeed(float s){
		speed = s;
	}
}
