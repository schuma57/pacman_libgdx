package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostYellow extends Ghost{

	public GhostYellow(float x, float y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		int nb = (int)( Math.random()*( 1 - 0 + 1 ) ) + 0;
		System.out.println("nb = "+nb);
		if(nb == 0)
			chaseMode();
		else
			noChaseMode();
	}
}
