package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pacman.iterators.WorldIterator;

public class World implements Iterable<GameElement>{
	private Maze maze;
	private Pacman pac;
	private List<Ghost> listGhosts;
	//private final int nbGhosts = 4;
	
	public World(){
		pac = new Pacman(1, 1);
	    maze = new Maze();
	    createGhosts();
	}
	
	private void createGhosts(){
		listGhosts = new ArrayList<Ghost>();
		//for(int i = 0 ; i < nbGhosts ; i++)
			listGhosts.add(new GhostRed(12, 16));
			listGhosts.add(new GhostBlue(13, 16));
			listGhosts.add(new GhostPink(14, 16));
			listGhosts.add(new GhostYellow(15, 16));
	}
	
	public int getHeight(){
		return maze.getHeight();
	}
	
	public int getWidth(){
		return maze.getWidth();
	}
	
	public Maze getMaze(){
		return maze;
	}
	
	public Pacman getPacman(){
		return pac;
	}

	public List<Ghost> getListGhosts(){
		return listGhosts;
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}
}
