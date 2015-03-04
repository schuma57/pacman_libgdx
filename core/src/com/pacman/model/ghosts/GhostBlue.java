package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostBlue extends Ghost{
	private int nb = 1;
	private int counter = 0;
	
	public GhostBlue(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		nb = (int)( Math.random()*( 1 - 0 + 1 ) ) + 0;
		
		if(nb == 0 || getLife() != GhostState.NORMAL){
			counter++;
			if(counter == MAX){
				chaseMode();
				System.out.println("Chase mode BLUE");
				counter = 0;
			}
		}
		else{
			noChaseMode();
			System.out.println("No Chase mode BLUE");
			autoMove();
		}	
	}
}
