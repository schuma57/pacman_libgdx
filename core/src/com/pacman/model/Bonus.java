package com.pacman.model;

public abstract class Bonus extends GameElement {
	
	public Bonus(float x, float y) {
		super(x, y);
	}
	
	public abstract int getPoints();
}
