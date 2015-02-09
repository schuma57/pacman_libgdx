package com.pacman.observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldListener implements InputProcessor{
	private World world;
	private Pacman pacman;
	
	public WorldListener(World w){
		world = w;
		pacman = world.getPacman();
	}

	@Override
	public boolean keyDown(int key) {
		if (key == Input.Keys.ESCAPE) {
			Gdx.app.exit();
		}
		return false;
	}

	@Override
	public boolean keyUp(int key) {
        if(key == Input.Keys.RIGHT){
        	pacman.setState(State.RIGHT);
        	if(pacman.getLastState() == State.LEFT)
        		pacman.setLastState(pacman.getState());
        }
        if(key == Input.Keys.LEFT){
        	pacman.setState(State.LEFT);
        	if(pacman.getLastState() == State.RIGHT)
        		pacman.setLastState(pacman.getState());
        }
        if(key == Input.Keys.DOWN){
        	pacman.setState(State.DOWN);
        	if(pacman.getLastState() == State.UP)
        		pacman.setLastState(pacman.getState());
        }
        if(key == Input.Keys.UP){
        	pacman.setState(State.UP);
        	if(pacman.getLastState() == State.DOWN)
        		pacman.setLastState(pacman.getState());
        }
		
		return false;
	}
	
	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

}
