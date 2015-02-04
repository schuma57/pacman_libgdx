package com.pacman.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.factories.TextureFactory;
import com.pacman.model.GameElement;
import com.pacman.model.Pacman;
import com.pacman.model.World;

public class WorldRenderer {
	private SpriteBatch sBatch;
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	
	public WorldRenderer(World world){
		this.world = world;
		sBatch = new SpriteBatch();
		//Gdx.input.setInputProcessor( world.getListener());
	}
	
	public void render(float delta){	
		sBatch.begin();
		world.getListener().movePacman(delta);
		for(GameElement element : world){
			renderElement(element);
		}
		sBatch.end();
	}
	
	private void renderElement(GameElement element ){
		String textur = null;
		if( !(element instanceof Pacman) )
			textur = element.getName();
		else{
			Pacman pac = (Pacman)element;
			textur = pac.getState().toString();	
		}
		
		sBatch.draw(
				TextureFactory.getInstance().getTexture(textur),
				element.getPosY() * boxSizeX,
				(world.getHeight() - element.getPosX() -1) * boxSizeY,
				boxSizeX,
				boxSizeY
		);
	}
	
	public void resize(float width, float height){
		setBoxSizeX( (float)width / (float)world.getWidth());
		setBoxSizeY( (float)height / (float)world.getHeight());
	}
	
	private void setBoxSizeX(float x){
		boxSizeX = x;
	}
	
	private void setBoxSizeY(float y){
		boxSizeY = y;
	}
	
	public SpriteBatch getBatch(){
		return sBatch;
	}

	public World getWorld(){
		return world;
	}
}
