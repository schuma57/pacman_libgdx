package com.pacman.model;

public class SuperPellet extends Bonus{
	private final int POINTS = 500;
	
	public SuperPellet(int x, int y) {
		super(x, y);
	}

	@Override
	public int getPoints() {
		return POINTS;
	}

}
