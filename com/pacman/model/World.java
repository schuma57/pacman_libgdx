package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pacman.iterators.WorldIterator;
import com.pacman.observers.WorldListener;

public class World implements Iterable<GameElement>{
	private WorldListener listener;
	
	private Maze maze;
	private Pacman pac;
	private List<Ghost> listGhosts;
	private int nbPoints;
	private boolean death;
	private boolean win;
	
	public World(){ 
		pac = new Pacman(1, 1);
	    maze = new Maze();
	    createGhosts();
	    nbPoints = 0;
	    death = false;
	    win = false;
	    listener = new WorldListener(this);
	}
	
	private void createGhosts(){
		listGhosts = new ArrayList<Ghost>();
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
	
	public int getNbPoints(){
		return nbPoints;
	}
	
	public void addPoints(int points){
		nbPoints += points;
	}
	
	public void removeElement(int x, int y){
		maze.removeElement(x, y);
	}
	
	public void death(){
		death = true;
	}
	
	public boolean isDeath(){
		return death;
	}
	
	public boolean isWin(){
		return win;
	}
	
	public void win(){
		win = true; 
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}
}
