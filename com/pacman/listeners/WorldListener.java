package com.pacman.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldListener implements InputProcessor{
	private World world;
	
	public WorldListener(World w){
		world = w;
	}
	
	@Override
	public boolean keyDown(int key) {
		if (key == Input.Keys.ESCAPE) {
			Gdx.app.exit();
		}
		
		movePacman(key);
		
		return false;
	}
	
	private void movePacman(int key){
		float posXFloat = world.getPacman().getPosition().get("x");
		float posYFloat = world.getPacman().getPosition().get("y");
		int x = (int)posXFloat;
		int y = (int)posYFloat;
		
		if(key == Input.Keys.LEFT){
			world.getPacman().setState(State.LEFT);
			if(y -1 >= 0){
				if( world.getMaze().getElement(x, y-1) == null  )
        			world.getPacman().setPositionY( y -1);
			}
        	else
        		world.getPacman().setPositionY( world.getWidth()-1);
        	
        }
        else if(key == Input.Keys.RIGHT){
        	world.getPacman().setState(State.RIGHT);
        	if(y+1 < world.getWidth()){
        		if( world.getMaze().getElement(x, y+1) == null  )
        			world.getPacman().setPositionY( y+1 );
        	}
        	else
    			world.getPacman().setPositionY(0);
        }
        else if(key == Input.Keys.DOWN){
        	world.getPacman().setState(State.DOWN);
        	if(x+1 < world.getHeight()){
        		if( world.getMaze().getElement(x+1, y) == null  )
        			world.getPacman().setPositionX( x +1 );
        	}
        	else
        		world.getPacman().setPositionX(0);
        }
        else if(key == Input.Keys.UP){
        	world.getPacman().setState(State.UP);
        	if(x-1 >= 0){
        		if( world.getMaze().getElement(x-1, y) == null  )
        			world.getPacman().setPositionX( x -1 );
        	}
        	else
        		world.getPacman().setPositionX( world.getHeight()-1 );
        }
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
