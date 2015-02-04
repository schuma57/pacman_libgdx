package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundFactory {
	private static SoundFactory instance = null;
	private Map<String, Sound> sounds;  
	
	private SoundFactory(){
		sounds = new HashMap<String, Sound>();
		sounds.put("chomp", Gdx.audio.newSound(Gdx.files.internal("sounds/pacman-chomp.ogg")));
		sounds.put("death", Gdx.audio.newSound(Gdx.files.internal("sounds/pacman-death.ogg")));
		sounds.put("eatFruit", Gdx.audio.newSound(Gdx.files.internal("sounds/pacman-eatfruit.ogg")));
		sounds.put("eatGhost", Gdx.audio.newSound(Gdx.files.internal("sounds/pacman-eatghost.ogg")));
		sounds.put("extraPac", Gdx.audio.newSound(Gdx.files.internal("sounds/pacman-extrapac.ogg")));
	}
	
	public static SoundFactory getInstance(){
		if(instance == null)
			instance = new SoundFactory();
		return instance;
	}
	
	public Sound getSound(String name){
		return sounds.get(name);
	}
}
