package com.pacman.model;

public abstract class MoveableElement extends GameElement{

	public MoveableElement(float x, float y) {
		super(x, y);
	}

	public void setPositionX(float x){
		this.posX = x;
	}
	
	public void setPositionY(float y){
		this.posY = y;
	}
}
