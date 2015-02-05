package com.pacman.observers;

import com.pacman.model.Block;
import com.pacman.model.Pacman;
import com.pacman.model.World;

public class PacmanObserver {
	private World world;
	private Pacman pac;
	
	public PacmanObserver(World w){
		world = w;
		pac = world.getPacman();
	}

	public void check(){
		if(blockExist(pac.getPosX(), pac.getPosY()+1)){
			Block b = (Block) world.getMaze().getElement((int)pac.getPosX(), (int)pac.getPosY()+1);
			System.out.println("le bloc existe");
			if(pac.getPosY()+20f+0.1f < b.getPosY()
					&& pac.getPosX() == b.getPosX())
				world.getPacman().setPositionY( pac.getPosY() + 0.1f );
			else
				System.out.println("pacman mal placé");
			
		}
		else{
			System.out.println("pas de bloc");
			world.getPacman().setPositionY( pac.getPosY() + 0.1f );
		}
		
		/*if(b != null){
			if(pac.getPosY() < b.getPosY())
				pac.setPositionY(pac.getPosY()+0.1f);
		}
		else
			pac.setPositionY(pac.getPosY()+0.1f);
		*/
	}
	
	private boolean blockExist(float x, float y){
		return (world.getMaze().getElement((int)x, (int)y) instanceof Block);
	}
}
