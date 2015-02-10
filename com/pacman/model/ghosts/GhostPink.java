package com.pacman.model.ghosts;

import com.pacman.model.World;

public class GhostPink extends Ghost {

	public GhostPink(float x, float y, World w) {
		super(x, y, w);
		setSpeed(1f);
	}

	@Override
	public void ghostMove() {
	}

}
