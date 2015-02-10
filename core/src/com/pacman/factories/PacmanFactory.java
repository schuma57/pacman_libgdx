package com.pacman.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PacmanFactory {
	private Animation anim;
	private Texture tex;
	private TextureRegion[] walk;
	private TextureRegion currentFrame;
	private float stateTime;
	
	
	public PacmanFactory(){
		create();
	}
	
	private void create(){
		tex = TextureFactory.getInstance().getTexture("Pacman");
		TextureRegion[][] temp = TextureRegion.split(tex, tex.getWidth()/4, tex.getHeight());
		walk = new TextureRegion[4];
		int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                walk[index++] = temp[i][j];
            }
        }
        
        anim = new Animation(0.125f, walk);
        stateTime = 0f; 
	}
	
	public TextureRegion getCurrentFrame(){
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = anim.getKeyFrame(stateTime, true);
        
		return currentFrame;
	}
}
