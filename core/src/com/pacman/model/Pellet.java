package com.pacman.model;

public class Pellet extends Bonus{
	private final int POINTS = 100;
	
	public Pellet(int x, int y) {
		super(x, y);
	}

	@Override
	public int getPoints() {
		return POINTS;
	}
}
