package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostYellow extends Ghost{
	private int counter = 0;
	
	public GhostYellow(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}
	
	@Override
	public void ghostMove() {
		if(getLife() == GhostState.NORMAL){
			noChaseMode();
			autoMove();
		}
		else{
			counter++;
			if(counter == MAX){
				chaseMode();
				counter = 0;
			}
		}
	}
}
