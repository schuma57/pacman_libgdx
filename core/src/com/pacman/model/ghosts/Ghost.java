package com.pacman.model.ghosts;


import java.util.ArrayList;

import algorithms.Astar;

import com.pacman.model.Block;
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
	
	private Astar astar = new Astar(getWorld().getMaze(), 10, false);
	ArrayList<Position> chemin = new ArrayList<Position>();
	
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
	
	protected void chaseMode(){
		if(getWorld().isInTheHouse(this)){
			targetX = 11;
			targetY = 13;
		}
		else{
			targetX = pacman.getPosX();
			targetY = pacman.getPosY();
		}
		
		/*if(getPosY() != targetY && getPosX() != targetX){
			
		}
		else if(getPosY() != targetY && getPosX() == targetX){
			if(getPosY() > targetY){
				if( !isAWall(getPosX(), getPosY()-1) )
					setLastState(State.LEFT);
			}
			else{
				if( !isAWall(getPosX(), getPosY()+1) )
					setLastState(State.LEFT);
			}
		}
		else if(getPosY() == targetY && getPosX() != targetX){
			if(getPosX() > targetX ){
				if( !isAWall(getPosX() -1, getPosY()) )
					setLastState(State.UP);
			}
			else{
				if( !isAWall(getPosX() +1, getPosY()) )
					setLastState(State.DOWN);
			}
		}*/
		
		chemin = astar.findPath(this, getPosX(), getPosY(), targetX, targetY);
		System.out.println(chemin);

		//TODO ne fonctionne pas, pacman meurt mais rien a l'ecran
		if(chemin != null && chemin.get(0) != null){
			//setPosition(chemin.get(0));
		}
		
		
		//setLastState(findDirection(targetX, targetY));
	}
	
	private boolean isAWall(int x, int y){
		return (getWorld().getMaze().getElement(x, y) instanceof Block);
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
	
	protected void escapePacman(){
		
	}
	
	private State findDirection(int targX, int targY) {
		State[] direction = new State[4] ;
		int deltaX = targX - getPosX() ;
		int deltaY = targY - getPosY() ;
		
		/*if(life == GhostState.AFRAID ){
			deltaX = -deltaX ;
			deltaY = -deltaY ;
		}*/
		
		if ((deltaX == 0) && (deltaY == 0))
			direction[0] = State.FRONT;
		else{
			if (Math.abs(deltaX) > Math.abs(deltaY))
			{
			 	if (deltaX > 0){
			 		direction[0] = State.DOWN;
			 		direction[3] = State.UP ;
			 	}else{
			    	direction[0] = State.UP;
			    	direction[3] = State.DOWN ;
			    }
			 	if (deltaY > 0){
			 		direction[1] = State.RIGHT;
			 		direction[2] = State.LEFT ;
			 	}else{
			 		direction[1] = State.LEFT;
			 		direction[2] = State.RIGHT ;
			 	}
			}
			else{
				if (deltaX > 0){
					direction[1] = State.DOWN;
					direction[2] = State.UP;
				}else{
			    	direction[1] = State.UP;
			    	direction[2] = State.DOWN;
				}	
			 	if (deltaY > 0){
			 		direction[0] = State.RIGHT;
			 		direction[3] = State.LEFT;
				}else{
			 		direction[0] = State.LEFT;
			 		direction[3] = State.RIGHT;
				}
			}
		}

		// On determine la direction la plus prioritaire qui ne pointe pas une brique.
		boolean sortie = false ;
		int i = 0 ;
		int new_x = getPosX();
		int new_y = getPosY() ;
		
		do
		{
			if (direction[i] == State.UP){
				new_x = getPosX()-1;
				new_y = getPosY();
			}
			else if (direction[i] == State.DOWN){
				new_x = getPosX()+1;
				new_y = getPosY();
			}
			else if (direction[i] == State.LEFT){
				new_x = getPosX();
				new_y = getPosY()-1;
			}
			else if (direction[i] == State.RIGHT){
				new_x = getPosX();
				new_y = getPosY()+1;
			}

			if ( !isAWall(new_x, new_y) )
				sortie = true ;
			
			i++;
		} while(sortie == false) ;

		if(i>3)
			System.out.println("Ghost.findDirection : Impossible...") ;
		
		return direction[i] ;
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
