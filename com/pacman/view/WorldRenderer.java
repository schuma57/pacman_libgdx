package com.pacman.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.model.Block;
import com.pacman.model.GameElement;
import com.pacman.model.World;

public class WorldRenderer {
	private SpriteBatch sprite;
	private float tailleCaseX;
	private float tailleCaseY;
	private World world;
	
	public WorldRenderer(World world){
		this.world = world;
		sprite = new SpriteBatch();
	}
	
	public void render(float delta){
		sprite.begin();
		
		for(GameElement element : world){
			if(element.getClass().equals(Block.class)){
				renderBlock(element);
			}
			else{
				renderOther(element);
			}
		}
		
		sprite.end();
	}
	
	private void renderBlock(GameElement block ){
		float posX = block.getPosition().get("y");
		float posY = world.getHeight() - (block.getPosition().get("x")) -1;
		sprite.draw(
				TextureFactory.getInstance().getTexture(block.getName()),
				posX * tailleCaseX,
				posY * tailleCaseY,
				tailleCaseX,
				tailleCaseY
		);
	}
	
	private void renderOther(GameElement element){
		sprite.draw(
				TextureFactory.getInstance().getTexture(element.getName()),
				element.getPosition().get("x") * tailleCaseX,
				element.getPosition().get("y") * tailleCaseY,
				tailleCaseX,
				tailleCaseY
		);
	}
	
	public void setBoxSizeX(float x){
		tailleCaseX = x;
	}
	
	public void setBoxSizeY(float y){
		tailleCaseY = y;
	}
	
}
