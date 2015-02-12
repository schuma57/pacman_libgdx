package com.pacman.model.ghosts;


import java.util.ArrayList;

import algorithms.Astar;
import com.pacman.model.MoveableElement;
import com.pacman.model.Pacman;
import com.pacman.model.Position;
import com.pacman.model.State;
import com.pacman.model.World;

public abstract class Ghost extends MoveableElement{
	private Pacman pacman;
	private GhostState life;
	protected int startX;
	protected int startY;
	protected int targetX = 0;
	protected int targetY = 0;
	
	private Astar astar = new Astar(getWorld().getMaze(), 25, false);
	private ArrayList<Position> chemin = new ArrayList<Position>();
	
	public Ghost(int x, int y, World w) {
		super(x, y, w);
		startX = x;
		startY = y;
		pacman = getWorld().getPacman();
		life = GhostState.NORMAL;
		setState(State.UP);
		//setLastState(State.UP);
	}
	
	public abstract void ghostMove();
	
	
	private void reset(){
		position = new Position(startX, startY);
		life = GhostState.NORMAL;
		setState(State.UP);
		setLastState(State.FRONT);
	}
	
	protected void noChaseMode(){
		if(getWorld().isInTheHouse(this)){
			targetX = 11;
			targetY = 13;
			
			if(getPosX() > targetX )
				setLastState(State.UP);
			else if(getPosX() < targetX)
				setLastState(State.DOWN);
			if(getPosY() > targetY)
				setLastState(State.LEFT);
			else if(getPosY() < targetY)
				setLastState(State.RIGHT);
		}
		else{
			setState(State.randomState());
		}
	}
	
	protected void chaseMode(){
		if(getWorld().isInTheHouse(this)){
			targetX = 11;
			targetY = 13;
		}
		else{
			targetX = pacman.getPosX();
			targetY = pacman.getPosY();
		}
		
		chemin = astar.findPath( getPosX(), getPosY(), targetX, targetY);
		if(chemin != null && chemin.get(1) != null)
			setPosition(chemin.get(chemin.size() - 2));	
	}
	
	protected void escapePacman(){	
	}
	
	public void flipDirection() {
		if (getState() == State.LEFT)
			setState( State.RIGHT);
		else if (getState() == State.RIGHT)
			setState( State.LEFT);
		else if (getState() == State.UP)
			setState(State.DOWN);
		else if(getState() == State.DOWN)
			setState( State.UP);
	}
	
	protected Pacman getPacman(){
		return pacman;
	}
	
	public boolean isAfraid(){
		return life == GhostState.AFRAID;
	}
	
	public boolean isDeath(){
		return life == GhostState.DEATH;
	}
	
	public void setLife(GhostState state){
		life = state;
	}
	
	public GhostState getLife(){
		return life;
	}
	
	public void findByAstar(Position start, Position goal ){
		
	}
   
}
