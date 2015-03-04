package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostRed extends Ghost{
	private final int WAIT_FOR_LEAVE = 1100;
	private int counter = 0;
	
	public GhostRed(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		if( this.isInTheHouse()){
			if( (System.currentTimeMillis() - timeToBegin) < WAIT_FOR_LEAVE){
				return;
			}
		}
		
		counter++;
		if(counter == MAX){
			chaseMode();
			counter = 0;
		}
	}

}
