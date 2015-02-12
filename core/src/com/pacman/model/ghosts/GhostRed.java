package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostRed extends Ghost{
	private int MAX = 13;
	private int counter = 0;
	
	public GhostRed(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		counter++;
		if(counter == MAX){
			chaseMode();
			counter = 0;
		}
	}

}
