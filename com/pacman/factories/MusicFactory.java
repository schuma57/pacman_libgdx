package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicFactory {
	private static MusicFactory instance = null;
	private Map<String, Music> musics;  
	
	private MusicFactory(){
		musics = new HashMap<String, Music>();
		musics.put("beginning", Gdx.audio.newMusic(Gdx.files.internal("sounds/pacman-beginning.ogg")));
		musics.put("interMission", Gdx.audio.newMusic(Gdx.files.internal("sounds/pacman-intermission.ogg")));
		musics.put("victory", Gdx.audio.newMusic(Gdx.files.internal("sounds/victory.ogg")));
	}
	
	public static MusicFactory getInstance(){
		if(instance == null)
			instance = new MusicFactory();
		return instance;
	}
	
	public Music getMusic(String name){
		return musics.get(name);
	}
}
