package com.pacman.adapters;

import com.badlogic.gdx.math.Vector2;
import com.pacman.model.Pacman;
import com.pacman.view.PacmanView;

public class PacmanAdapter {
	private PacmanView pacmanView;
	
	public PacmanAdapter(){
		//empty
	}
	
	public PacmanView adaptePacman(Pacman pac, float width, float height){
		pacmanView = new PacmanView(new Vector2(pac.getPosX(), pac.getPosY()), width, height);
		return pacmanView;
	}
}
