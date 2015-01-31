package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pacman.iterators.WorldIterator;
import com.pacman.listeners.WorldListener;

public class World implements Iterable<GameElement>{
	public static final float moveSpeed = 0.5f;
	private WorldListener listener;
	
	private Maze maze;
	private Pacman pac;
	private List<Ghost> listGhosts;
	//private final int nbGhosts = 4;
	
	public World(){ 
		pac = new Pacman(1, 1);
	    maze = new Maze();
	    createGhosts();
	    listener = new WorldListener(this);
	}
	
	private void createGhosts(){
		listGhosts = new ArrayList<Ghost>();
		//for(int i = 0 ; i < nbGhosts ; i++)
			listGhosts.add(new GhostRed(14, 12));
			listGhosts.add(new GhostBlue(14, 13));
			listGhosts.add(new GhostPink(14, 14));
			listGhosts.add(new GhostYellow(14, 15));
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
	
	public WorldListener getListener(){
		return listener;
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}
}
