package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostYellow extends Ghost{
	private int nb = 1;
	
	public GhostYellow(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}
	
	@Override
	public void ghostMove() {
		if(getWorld().hasIntersection(this)){
			nb = (int)( Math.random()*( 1 - 0 + 1 ) ) + 0;
			System.out.println("nb = "+nb);
		}
		
		if(nb == 0)
			chaseMode();
		else{
			noChaseMode();
			autoMove();
		}
	}
}
