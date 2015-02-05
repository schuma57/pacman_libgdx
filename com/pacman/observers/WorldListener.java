package com.pacman.observers;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.pacman.model.Block;
import com.pacman.model.Bonus;
import com.pacman.model.GameElement;
import com.pacman.model.Ghost;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldListener{
	private World world;
	
	public WorldListener(World w){
		world = w;
	}
	
	public void movePacman(float delta){
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		float posXFloat = world.getPacman().getPosX();
		float posYFloat = world.getPacman().getPosY();
		float speed = world.getPacman().getSpeed();

		
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	int x = (int)posXFloat;
        	int y = (int)Math.floor(posYFloat);
        	world.getPacman().setState(State.RIGHT);
        	if(y+1 < world.getWidth()){
        		GameElement element = world.getMaze().getElement(x, y+1);
        		if( !(element instanceof Block)){
        			
        			world.getPacman().setPositionY( posYFloat + speed );
        			if(element instanceof Bonus){
        				world.addPoints(((Bonus) element).getPoints());
        				world.removeElement(x, y);
        			}
        		}
        	}
        	else
    			world.getPacman().setPositionY(0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        	int x = (int)posXFloat;
        	int y = (int)Math.ceil(posYFloat);
			world.getPacman().setState(State.LEFT);
			if(y -1 >= 0){
				GameElement element = world.getMaze().getElement(x, y-1);
				if( !(element instanceof Block) ){
        			world.getPacman().setPositionY( posYFloat -speed);
        			if(element instanceof Bonus){
        				world.addPoints(((Bonus) element).getPoints());
        				world.removeElement(x, y);
        			}
				}
			}
        	else
        		world.getPacman().setPositionY( world.getWidth()-1);
        	
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	int x = (int)Math.floor(posXFloat);
        	int y = (int)posYFloat;
        	world.getPacman().setState(State.DOWN);
        	if(x+1 < world.getHeight()){
        		GameElement element = world.getMaze().getElement(x+1, y);
        		if( !(element instanceof Block)  ){
        			world.getPacman().setPositionX( posXFloat + speed);
        			if(element instanceof Bonus){
        				world.addPoints(((Bonus) element).getPoints());
        				world.removeElement(x, y);
        			}
        		}
        	}
        	else
        		world.getPacman().setPositionX(0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        	int x = (int)Math.ceil(posXFloat);
        	int y = (int)posYFloat;
        	world.getPacman().setState(State.UP);
        	if(x-1 >= 0){
        		GameElement element = world.getMaze().getElement(x-1, y);
        		if( !(element instanceof Block)  ){
        			world.getPacman().setPositionX( posXFloat - speed );
        			if(element instanceof Bonus){
        				world.addPoints(((Bonus) element).getPoints());
        				world.removeElement(x, y);
        			}
        		}
        	}
        	else
        		world.getPacman().setPositionX( world.getHeight()-1 );
        }
	}
	
	public void testDeath(){
		Pacman pac = world.getPacman();
		List<Ghost> ghosts = world.getListGhosts();
		for(Ghost g : ghosts){
			if(g.getPosX() == pac.getPosX() && g.getPosY() == pac.getPosY())
				world.death();
		}
		
	}
	
	public void testWin(){
		if(world.getMaze().getNbPellets() <= 0)
			world.win();
	}

}
