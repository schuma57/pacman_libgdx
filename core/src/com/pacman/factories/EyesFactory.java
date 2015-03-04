package com.pacman.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.model.State;

public class EyesFactory {
	private Map<State, TextureRegion> eyes;
	private Texture textureEyes;
	private TextureRegion[] regionEyes;
	
	private static EyesFactory instance = null;
	
	private EyesFactory(){
		createEyes();
		
		eyes = new HashMap<State, TextureRegion>();
		eyes.put(State.UP, 		regionEyes[0]);
		eyes.put(State.DOWN, 	regionEyes[1]);
		eyes.put(State.LEFT, 	regionEyes[2]);
		eyes.put(State.RIGHT, 	regionEyes[3]);
		eyes.put(State.FRONT, 	regionEyes[1]);
	}
	
	private void createEyes() {
		textureEyes = TextureFactory.getInstance().getTexture("EYES");
		TextureRegion[][] temp = TextureRegion.split(textureEyes, textureEyes.getWidth()/2, textureEyes.getHeight()/2);
		regionEyes = new TextureRegion[4];
		int index = 0;
        for (int i = 0 ; i < 2 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                regionEyes[index++] = temp[i][j];
            }
        }
	}
	
	public static EyesFactory getInstance(){
		if(instance == null)
			instance = new EyesFactory();
		return instance;
	}
	
	public TextureRegion getEyes(State state){
		if(state != null)
			return eyes.get(state);
		else
			return eyes.get(State.FRONT);
	}
	
}
