package com.pacman.game;

import com.badlogic.gdx.Game;
import com.pacman.screens.GameScreen;

public class PacManGame extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameScreen());
	}
}
