package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostBlue extends Ghost{
	private int MAX = 10;
	private int nb = 1;
	private int counter = 0;
	
	public GhostBlue(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		counter++;
		if(counter == MAX){
			nb = (int)( Math.random()*( 1 - 0 + 1 ) ) + 0;
				
			if(nb == 0){
				chaseMode();
				System.out.println("Chase mode BLUE");
			}
			else{
				noChaseMode();
				autoMove();
			}
			counter = 0;
		}
	}
}
