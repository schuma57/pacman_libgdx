package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.pacman.game.ConstantsGame;

public class SoundFactory {
	private static SoundFactory instance = null;
	private Map<String, Sound> sounds;  
	
	private SoundFactory(){
		sounds = new HashMap<String, Sound>();
		sounds.put("chomp", Gdx.audio.newSound(Gdx.files.internal(ConstantsGame.CHOMP_SOUND)));
		sounds.put("death", Gdx.audio.newSound(Gdx.files.internal(ConstantsGame.DEATH_SOUND)));
		sounds.put("eatFruit", Gdx.audio.newSound(Gdx.files.internal(ConstantsGame.EATFRUIT_SOUND)));
		sounds.put("eatGhost", Gdx.audio.newSound(Gdx.files.internal(ConstantsGame.EATGHOST_SOUND)));
		sounds.put("extraPac", Gdx.audio.newSound(Gdx.files.internal(ConstantsGame.EXTRAPAC_SOUND)));
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
