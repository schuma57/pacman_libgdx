package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.pacman.model.Block;
import com.pacman.model.GhostBlue;
import com.pacman.model.GhostPink;
import com.pacman.model.GhostRed;
import com.pacman.model.GhostYellow;
import com.pacman.model.Pacman;
import com.pacman.model.Pellet;
import com.pacman.model.SuperPellet;

public class TextureFactory {
	private static TextureFactory instance = null;
	private Map<String, Texture> textures;
	
	private TextureFactory(){
		textures = new HashMap<String, Texture>();
		textures.put("Pacman", new Texture("images/PacManAnim.png"));
		textures.put(Pacman.class.toString(), new Texture("images/pacmanRight.png"));
		textures.put(Block.class.toString(), new Texture("images/bloc.png"));
		textures.put(GhostRed.class.toString(), new Texture("images/ghost1.png"));
		textures.put(GhostBlue.class.toString(), new Texture("images/ghost3.png"));
		textures.put(GhostPink.class.toString(), new Texture("images/ghost2.png"));
		textures.put(GhostYellow.class.toString(), new Texture("images/ghost4.png"));
		textures.put(Pellet.class.toString(), new Texture("images/pellet.png"));
		textures.put(SuperPellet.class.toString(), new Texture("images/superpellet-2.png"));
		textures.put("Right", new Texture("images/pacmanRight.png"));
		textures.put("Left", new Texture("images/pacmanLeft.png"));
		textures.put("Up", new Texture("images/pacmanUp.png"));
		textures.put("Down", new Texture("images/pacmanDown.png"));
		textures.put("Front", new Texture("images/pacman-3.png"));
		textures.put("Pellet", new Texture("images/pellet.png"));
	}
	
	public static TextureFactory getInstance(){
		if (instance == null)
			return (instance = new TextureFactory());
		else
			return instance;
	}
	
	public static void reset(){
		instance = null;
	}
	
	public Texture getTexture(String name){
		return textures.get(name);
	}
}
