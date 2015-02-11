package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostRed extends Ghost{

	public GhostRed(int x, int y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		chaseMode();
	}

}
