package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostPink extends Ghost {
	private int counter = 0;
	private final int WAIT_FOR_LEAVE = 4000;

	public GhostPink(int x, int y, World w) {
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
