package com.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pacman.model.GameElement;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldRenderer extends InputListener{
	private SpriteBatch sBatch;
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	
	
	public WorldRenderer(World world){
		this.world = world;
		sBatch = new SpriteBatch();
	}
	
	public void render(float delta){	
        movePacman();
		sBatch.begin();
		
		for(GameElement element : world){
			renderElement(element);
		}
		sBatch.end();
	}
	
	private void movePacman(){
		float posXFloat = world.getPacman().getPosition().get("x");
		float posYFloat = world.getPacman().getPosition().get("y");
		int x = (int)posXFloat;
		int y = (int)posYFloat;
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			world.getPacman().setState(State.UP);
			if(y -1 >= 0){
				if( world.getMaze().getElement(x, y-1) == null  )
        			world.getPacman().setPositionY( y -1);
			}
        	else
        		world.getPacman().setPositionY( world.getWidth()-1);
        	
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	world.getPacman().setState(State.RIGHT);
        	if(y+1 < world.getWidth()){
        		if( world.getMaze().getElement(x, y+1) == null  )
        			world.getPacman().setPositionY( y+1 );
        	}
        	else
    			world.getPacman().setPositionY(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	world.getPacman().setState(State.DOWN);
        	if(x+1 < world.getHeight()){
        		if( world.getMaze().getElement(x+1, y) == null  )
        			world.getPacman().setPositionX( x +1 );
        	}
        	else
        		world.getPacman().setPositionX(0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        	world.getPacman().setState(State.UP);
        	if(x-1 >= 0){
        		if( world.getMaze().getElement(x-1, y) == null  )
        			world.getPacman().setPositionX( x -1 );
        	}
        	else
        		world.getPacman().setPositionX( world.getHeight()-1 );
        }
	}
	
	private void renderElement(GameElement element ){
		String textur = null;
		if(!element.getName().equals(Pacman.class.toString()) )
			textur = element.getName();
		else{
			Pacman pac = (Pacman)element;
			textur = pac.getState().toString();	
		}
		
		sBatch.draw(
				TextureFactory.getInstance().getTexture(textur),
				element.getPosition().get("y") * boxSizeX,
				(world.getHeight() - element.getPosition().get("x") -1) * boxSizeY,
				boxSizeX,
				boxSizeY
		);
	}
	
	public void setBoxSizeX(float x){
		boxSizeX = x;
	}
	
	public void setBoxSizeY(float y){
		boxSizeY = y;
	}
	
	public SpriteBatch getBatch(){
		return sBatch;
	}
	
}
