package com.pacman.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.pacman.model.GameElement;
import com.pacman.model.Ghost;
import com.pacman.model.World;

public class WorldIterator implements Iterator<GameElement>{
	private World world;
	private Iterator<GameElement> mazeIterator;
	private Iterator<Ghost> ghostIterator;
	private int current;
	
	public WorldIterator(World world){
		this.world = world;
		mazeIterator = world.getMaze().iterator();
		ghostIterator = world.getListGhosts().iterator();
		current = 0;	//0 = maze, 1 = ghost, 2 = pacman
	}
	
	@Override
	public boolean hasNext() {
		return current < 2;
	}

	@Override
	public GameElement next() {
		if(!this.hasNext())
			throw new NoSuchElementException("GameElement list is empty");
		
		if(current == 0){
			if(!mazeIterator.hasNext())
				current = 1;
		}
		if(current == 1){
			if(!ghostIterator.hasNext())
				current = 2;
		}
		
		switch(current){
			case 0: return mazeIterator.next();
			case 1: return ghostIterator.next();
			default: return world.getPacman();
		}
	}

	@Override
	public void remove() {
		// empty
	}
}
