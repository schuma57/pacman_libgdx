package com.pacman.model.ghosts;


import java.util.ArrayList;

import com.pacman.algorithms.Astar;
import com.pacman.model.MoveableElement;
import com.pacman.model.Pacman;
import com.pacman.model.Position;
import com.pacman.model.State;
import com.pacman.model.World;

public abstract class Ghost extends MoveableElement{
	private final int MAX_FOR_ASTAR = 45;
	private final long MILLISECONDS_FOR_AFRAID = 9000;
	
	private Pacman pacman;
	private GhostState life;
	protected int startX;
	protected int startY;
	protected int targetX = 0;
	protected int targetY = 0;
	protected long timeToAfraid;
	protected long timeToBegin;
	
	private Astar astar = new Astar(getWorld().getMaze(), MAX_FOR_ASTAR, false);
	private ArrayList<Position> path = new ArrayList<Position>();
	
	public Ghost(int x, int y, World w) {
		super(x, y, w);
		startX = x;
		startY = y;
		pacman = getWorld().getPacman();
		life = GhostState.NORMAL;
		setState(State.UP);
		timeToBegin = System.currentTimeMillis();
	}
	
	public abstract void ghostMove();
	
	
	private void reset(){
		position = new Position(startX, startY);
		life = GhostState.NORMAL;
		setState(State.UP);
		setLastState(State.FRONT);
		timeToBegin = System.currentTimeMillis();
	}
	
	protected void noChaseMode(){
		if(this.isInTheHouse()){
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
		if( isAfraid() ){
			escapePacman();
		}
		else if( isDeath() ){
			targetX = startX;
			targetY = startY;
			if(getPosX() == startX && getPosY() == startY)
				reset();
		}
		else if(this.isInTheHouse()){
			targetX = 11;
			targetY = 13;
		}
		else{
			targetX = pacman.getPosX();
			targetY = pacman.getPosY();
		}
		
		path = astar.findPath( getPosX(), getPosY(), targetX, targetY);
		if(path != null && path.get(path.size()-2) != null){
			Position nextPosition = path.get(path.size() - 2);
			detectDirection(nextPosition);
			setPosition(nextPosition);
		}
	}
	
	protected void escapePacman(){
		if( (System.currentTimeMillis() - timeToAfraid) >= MILLISECONDS_FOR_AFRAID){
			setLife(GhostState.NORMAL);
			return;
		}
		targetX = getWorld().getHeight()-1 - pacman.getPosX();
		targetY = getWorld().getWidth()-1 - pacman.getPosY();
	}
	
	private void detectDirection(Position next){
		if(next.getX() != this.getPosX()){
			if(next.getX() > this.getPosX())
				setLastState(State.DOWN);
			else
				setLastState(State.UP);
		}
		else if(next.getY() != this.getPosY()){
			if(next.getY() > this.getPosY())
				setLastState(State.RIGHT);
			else
				setLastState(State.LEFT);
		}
	}
	
	protected Pacman getPacman(){
		return pacman;
	}
	
	protected boolean isInTheHouse(){
		return getWorld().isInTheHouse(this);
	}
	
	public boolean isAfraid(){
		return life == GhostState.AFRAID;
	}
	
	public boolean isDeath(){
		return life == GhostState.DEATH;
	}
	
	public boolean isNormal(){
		return life == GhostState.NORMAL;
	}
	
	public void setLifeToAfraid(){
		life = GhostState.AFRAID;
		timeToAfraid = System.currentTimeMillis();
	}
	
	public long getTimeToAfraid(){
		return timeToAfraid;
	}
	
	public void setLife(GhostState state){
		life = state;
	}
	
	public GhostState getLife(){
		return life;
	}
   
}
