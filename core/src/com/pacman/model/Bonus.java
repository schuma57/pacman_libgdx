package com.pacman.model;

public abstract class Bonus extends GameElement {
	
	public Bonus(int x, int y) {
		super(x, y);
	}
	
	public abstract int getPoints();
}
