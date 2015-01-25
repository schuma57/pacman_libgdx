package com.pacman.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.pacman.model.GameElement;
import com.pacman.model.Maze;

public class MazeIterator implements Iterator<GameElement> {
	private Maze maze;
	private int x, y;
	
	public MazeIterator(Maze maze){
		this.maze = maze;
		x = y = 0;
	}

	@Override
	public boolean hasNext() {
		return (x < maze.getHeight() && y < maze.getWidth());
	}

	@Override
	public GameElement next() {
		if(!this.hasNext())
			throw new NoSuchElementException("GameElement List is empty");
		
	    GameElement element;
	    do {
	    	element = maze.getElement(x,y);
	    	y = (y + 1) % maze.getWidth();
	    	if(y == 0)
	    		x++;
	    } while(element == null && this.hasNext());
	    
	    return element;
	}

	@Override
	public void remove() {
		// empty
	}
}
