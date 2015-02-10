package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostBlue extends Ghost{

	public GhostBlue(float x, float y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
		noChaseMode();
	}
}
