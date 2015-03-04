package com.pacman.factories;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.model.ghosts.GhostBlue;
import com.pacman.model.ghosts.GhostPink;
import com.pacman.model.ghosts.GhostRed;
import com.pacman.model.ghosts.GhostState;
import com.pacman.model.ghosts.GhostYellow;

public class GhostsFactory {
	private final float TIME = 0.75f;
	
	private Animation animRed;
	private Animation animPink;
	private Animation animBlue;
	private Animation animYellow;
	private Animation animAfraid;
	
	private TextureRegion[] walkRed;
	private TextureRegion[] walkPink;
	private TextureRegion[] walkBlue;
	private TextureRegion[] walkYellow;
	private TextureRegion[] walkAfraid;
	
	private TextureRegion currentFrameRed;
	private TextureRegion currentFramePink;
	private TextureRegion currentFrameBlue;
	private TextureRegion currentFrameYellow;
	private TextureRegion currentFrameAfraid;
	
	private float stateTimeRed;
	private float stateTimeBlue;
	private float stateTimePink;
	private float stateTimeYellow;
	private float stateTimeAfraid;
	
	private Texture textureRed;
	private Texture texturePink;
	private Texture textureBlue;
	private Texture textureYellow;
	private Texture textureAfraid;
	
	
	public GhostsFactory(){	
		createRed();
		createBlue();
		createPink();
		createYellow();
		createAfraid();
	}

	private void createYellow() {
		textureYellow = TextureFactory.getInstance().getTexture(GhostYellow.class.toString());
		TextureRegion[][] temp = TextureRegion.split(textureYellow, textureYellow.getWidth()/2, textureYellow.getHeight());
		walkYellow = new TextureRegion[2];
		int index = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                walkYellow[index++] = temp[i][j];
            }
        }
        
        animYellow = new Animation(TIME, walkYellow);
        stateTimeYellow = 0f;
	}

	private void createPink() {
		texturePink = TextureFactory.getInstance().getTexture(GhostPink.class.toString());
		TextureRegion[][] temp = TextureRegion.split(texturePink, texturePink.getWidth()/2, texturePink.getHeight());
		walkPink = new TextureRegion[2];
		int index = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                walkPink[index++] = temp[i][j];
            }
        }
        
        animPink = new Animation(TIME, walkPink);
        stateTimePink = 0f; 
	}

	private void createBlue() {
		textureBlue = TextureFactory.getInstance().getTexture(GhostBlue.class.toString());
		TextureRegion[][] temp = TextureRegion.split(textureBlue, textureBlue.getWidth()/2, textureBlue.getHeight());
		walkBlue = new TextureRegion[2];
		int index = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                walkBlue[index++] = temp[i][j];
            }
        }
        
        animBlue = new Animation(TIME, walkBlue);
        stateTimeBlue = 0f;
	}

	private void createRed() {
		textureRed = TextureFactory.getInstance().getTexture(GhostRed.class.toString());
		TextureRegion[][] temp = TextureRegion.split(textureRed, textureRed.getWidth()/2, textureRed.getHeight());
		walkRed = new TextureRegion[2];
		int index = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                walkRed[index++] = temp[i][j];
            }
        }
        
        animRed = new Animation(TIME, walkRed);
        stateTimeRed = 0f;
	}
	
	private void createAfraid() {
		textureAfraid = TextureFactory.getInstance().getTexture(GhostState.AFRAID.toString());
		TextureRegion[][] temp = TextureRegion.split(textureAfraid, textureAfraid.getWidth()/2, textureAfraid.getHeight());
		walkAfraid = new TextureRegion[2];
		int index = 0;
        for (int i = 0 ; i < 1 ; i++) {
            for (int j = 0 ; j < 2 ; j++) {
                walkAfraid[index++] = temp[i][j];
            }
        }
        
        animAfraid = new Animation(TIME, walkAfraid);
        stateTimeAfraid = 0f;
	}
	
	
	private TextureRegion getCurrentFrameRed(){
		stateTimeRed += Gdx.graphics.getDeltaTime();
        currentFrameRed = animRed.getKeyFrame(stateTimeRed, true);
        
		return currentFrameRed;
	}
	
	private TextureRegion getCurrentFramePink(){
		stateTimePink += Gdx.graphics.getDeltaTime();
        currentFramePink = animPink.getKeyFrame(stateTimePink, true);
        
		return currentFramePink;
	}
	
	private TextureRegion getCurrentFrameBlue(){
		stateTimeBlue += Gdx.graphics.getDeltaTime();
        currentFrameBlue = animBlue.getKeyFrame(stateTimeBlue, true);
        
		return currentFrameBlue;
	}
	
	private TextureRegion getCurrentFrameYellow(){
		stateTimeYellow += Gdx.graphics.getDeltaTime();
        currentFrameYellow = animYellow.getKeyFrame(stateTimeYellow, true);
        
		return currentFrameYellow;
	}
	
	public TextureRegion getCurrentFrameAfraid(){
		stateTimeAfraid += Gdx.graphics.getDeltaTime();
        currentFrameAfraid = animAfraid.getKeyFrame(stateTimeAfraid, true);
        
		return currentFrameAfraid;
	}
	
	public TextureRegion getCurrenFrameGhost(Class<?> ghost){
		if(ghost == GhostPink.class)
			return getCurrentFramePink();
		else if(ghost == GhostBlue.class)
			return getCurrentFrameBlue();
		else if(ghost == GhostRed.class)
			return getCurrentFrameRed();
		else if(ghost == GhostYellow.class)
			return getCurrentFrameYellow();
		
		return null;
	}
	
}
