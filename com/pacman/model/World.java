package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pacman.iterators.WorldIterator;
import com.pacman.listeners.WorldListener;
import com.pacman.model.ghosts.Ghost;
import com.pacman.model.ghosts.GhostBlue;
import com.pacman.model.ghosts.GhostPink;
import com.pacman.model.ghosts.GhostRed;
import com.pacman.model.ghosts.GhostYellow;

public class World implements Iterable<GameElement>{
	private WorldListener listener;
	
	private Maze maze;
	private Pacman pac;
	private List<Ghost> listGhosts;
	private int nbPoints;
	private boolean eatGhost;
	private boolean death;
	private boolean win;
	
	public World(){ 
		pac = new Pacman(1, 1, this);
	    maze = new Maze();
	    createGhosts();
	    nbPoints = 0;
	    death = win = eatGhost = false;
	    listener = new WorldListener(this);
	}
	
	private void createGhosts(){
		listGhosts = new ArrayList<Ghost>();
		listGhosts.add(new GhostRed(14, 12, this));
		listGhosts.add(new GhostBlue(14, 13, this));
		listGhosts.add(new GhostPink(14, 14, this));
		listGhosts.add(new GhostYellow(14, 15, this));
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
	
	public boolean hasWin(){
		return win;
	}
	
	public void winGame(){
		win = true; 
	}
	
	public void ghostsAreAfraid(){
		/*for(Ghost g : listGhosts){
			g.setLife(GhostState.AFRAID);
		}*/
	}
	
	public boolean hasIntersection(MoveableElement element){
		float posXFloat = element.getPosX();
		float posYFloat = element.getPosY();
		int x=0; int y=0;
		
		if(element.getLastState() == State.LEFT){
			x = Math.round(posXFloat);
			y = Math.round(posYFloat);
		}
		else if(element.getLastState() == State.RIGHT){
			x = Math.round(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else if(element.getLastState() == State.UP){
			x = Math.round(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else if(element.getLastState() == State.DOWN){
			x = (int)Math.floor(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else{
			x = (int)posXFloat;
			y = (int)posYFloat;
		}
		
		return maze.isIntersection(x, y);
	}
	
	public boolean isInTheHouse(Ghost ghost){
		float posXFloat = ghost.getPosX();
		float posYFloat = ghost.getPosY();
		int x=0; int y=0;
		
		if(ghost.getLastState() == State.LEFT){
			x = Math.round(posXFloat);
			y = Math.round(posYFloat);
		}
		else if(ghost.getLastState() == State.RIGHT){
			x = Math.round(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else if(ghost.getLastState() == State.UP){
			x = Math.round(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else if(ghost.getLastState() == State.DOWN){
			x = (int)Math.floor(posXFloat);
			y = (int) Math.floor(posYFloat);
		}
		else{
			x = (int)posXFloat;
			y = (int)posYFloat;
		}
		
		return maze.isInTheHouse(x, y);
	}
	
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}
}
