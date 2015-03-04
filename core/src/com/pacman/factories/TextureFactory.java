package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.pacman.model.Block;
import com.pacman.model.Pacman;
import com.pacman.model.Pellet;
import com.pacman.model.SuperPellet;
import com.pacman.model.ghosts.GhostBlue;
import com.pacman.model.ghosts.GhostPink;
import com.pacman.model.ghosts.GhostRed;
import com.pacman.model.ghosts.GhostState;
import com.pacman.model.ghosts.GhostYellow;
import com.pacman.game.ConstantsGame;

public class TextureFactory {
	private static TextureFactory instance = null;
	private Map<String, Texture> textures;
	
	private TextureFactory(){
		textures = new HashMap<String, Texture>();
		textures.put("Pacman", new Texture(ConstantsGame.PACMAN_IMG));
		textures.put(Pacman.class.toString(), new Texture(ConstantsGame.PACMAN_IMG));
		textures.put(Block.class.toString(), new Texture(ConstantsGame.BLOC_IMG));
		textures.put(GhostRed.class.toString(), new Texture(ConstantsGame.BLINKY_IMG));
		textures.put(GhostBlue.class.toString(), new Texture(ConstantsGame.INKY_IMG));
		textures.put(GhostPink.class.toString(), new Texture(ConstantsGame.PINKY_IMG));
		textures.put(GhostYellow.class.toString(), new Texture(ConstantsGame.CLYDE_IMG));
		textures.put(GhostState.AFRAID.toString(), new Texture(ConstantsGame.GHOST_AFRAID));
		textures.put(GhostState.DEATH.toString(), new Texture(ConstantsGame.GHOST_DEAD));
		textures.put("WHITE", new Texture(ConstantsGame.GHOST_WHITE));
		textures.put("EYES", new Texture(ConstantsGame.GHOST_EYES));
		textures.put(Pellet.class.toString(), new Texture(ConstantsGame.PELLET_IMG));
		textures.put(SuperPellet.class.toString(), new Texture(ConstantsGame.SUPERPELLET_IMG));
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
