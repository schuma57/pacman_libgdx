package com.pacman.model;

public abstract class MoveableElement extends GameElement{
	private final int MAX = 9;
	
	private World world;
	private State state;
	private State lastState;
	private float speed;
	private int moveCount = 0;
	
	public MoveableElement(int x, int y, World w) {
		super(x, y);
		world = w;
	}

	public void setPositionX(int x){
		getPosition().setX(x);
	}
	
	public void setPositionY(int y){
		getPosition().setY(y);
	}
	
	public void setPosition(Position pos){
		position = pos;
	}
	
	protected World getWorld(){
		return world;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State s){
		state = s;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public void setSpeed(float s){
		speed = s;
	}

	public State getLastState() {
		return lastState;
	}

	public void setLastState(State lastState) {
		this.lastState = lastState;
	}
	
	public void autoMove() {
		moveCount++;
		if(moveCount == MAX){
			if(world.hasIntersection(this))
	    		setLastState(state);
			
			if( lastState == State.RIGHT){
				if(!isOutOfBounds()){
					if(!isGoingToHitAWall())
						setPositionY( getPosY() + 1 );
				}
				else
					setPositionY(0);
			}
			else if( lastState == State.LEFT){
				if(!isOutOfBounds()){
					if(!isGoingToHitAWall())
						setPositionY( getPosY() - 1 );
				}
				else
					setPositionY( world.getWidth()-1);
			}
			else if( lastState == State.UP){
				if(!isOutOfBounds()){
					if(!isGoingToHitAWall())
						setPositionX( getPosX() - 1 );
				}
				else
					setPositionX( world.getHeight()-1 );
			}
			else if( lastState == State.DOWN){
				if(!isOutOfBounds()){
					if(!isGoingToHitAWall())
						setPositionX( getPosX() + 1 );
				}
				else
					setPositionX(0);
			}
			moveCount = 0;
		}
	}
	
	protected boolean isGoingToHitAWall(){
		GameElement ge = null;
		if(getLastState() == State.LEFT){
			ge = world.getMaze().getElement(getPosX(), getPosY() -1);
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.RIGHT){
			ge = world.getMaze().getElement(getPosX(), getPosY() +1);
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.UP){
			ge = world.getMaze().getElement(getPosX() -1, getPosY());
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.DOWN){
			ge = world.getMaze().getElement(getPosX() +1, getPosY());
			if(ge instanceof Block)
				return true;
		}
		
		return false;
	}
	
	private boolean isOutOfBounds(){
		if(getLastState() == State.LEFT){
			if( getPosY() -1 < 0 ) return true;
		}
		else if(getLastState() == State.RIGHT){
			if( getPosY() +1 > world.getWidth() -1 ) return true;
		}
		else if(getLastState() == State.UP){
			if( getPosX() - 1 < 0 ) return true;
		}
		else if(getLastState() == State.DOWN){
			if( getPosX() + 1 > world.getHeight() -1 ) return true;
		}
		
		return false;
	}
	
}
