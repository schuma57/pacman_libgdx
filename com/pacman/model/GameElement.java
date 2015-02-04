package com.pacman.model;

public abstract class GameElement {
	protected float posX;
	protected float posY;
	
	public GameElement(float x, float y){
		posX = x;
		posY = y;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public String getName(){
		return this.getClass().toString();
	}
}
