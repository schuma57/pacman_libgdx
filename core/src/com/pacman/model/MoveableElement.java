package com.pacman.model;

public abstract class MoveableElement extends GameElement{
	private World world;
	private State state;
	private State lastState;
	private float speed;
	
	public MoveableElement(float x, float y, World w) {
		super(x, y);
		world = w;
	}

	public void setPositionX(float x){
		this.posX = x;
	}
	
	public void setPositionY(float y){
		this.posY = y;
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
		if(world.hasIntersection(this))
    		setLastState(state);
		
		if( lastState == State.RIGHT){
			if(!isWall())
				setPositionY( posY + speed );
			testOutOfBounds();
		}
		else if( lastState == State.LEFT){
			if(!isWall())
				setPositionY( posY - speed );
			testOutOfBounds();
		}
		else if( lastState == State.UP){
			if(!isWall())
				setPositionX( posX - speed );
			testOutOfBounds();
		}
		else if( lastState == State.DOWN){
			if(!isWall())
				setPositionX( posX + speed );
			testOutOfBounds();
		}
	}
	
	protected boolean isWall(){
		float posXFloat = getPosX();
		float posYFloat = getPosY();
		
		if(getLastState() == State.LEFT){
			GameElement ge =
					world.getMaze().getElement((int)posXFloat, (int) Math.round(posYFloat) -1);
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.RIGHT){
			GameElement ge =
					world.getMaze().getElement((int)Math.round(posXFloat), (int) Math.floor(posYFloat)+1);
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.UP){
			GameElement ge =
					world.getMaze().getElement((int)Math.round(posXFloat)-1, (int)posYFloat);
			if(ge instanceof Block)
				return true;
		}
		else if(getLastState() == State.DOWN){
			GameElement ge =
					world.getMaze().getElement((int)Math.floor(posXFloat)+1, (int)posYFloat);
			if(ge instanceof Block)
				return true;
		}
		
		return false;
	}
	
	private void testOutOfBounds(){
		if(getLastState() == State.LEFT){
			if(getPosY() - speed >= 0){	
			}
			else
        		setPositionY( world.getWidth()-1);
		}
		else if(getLastState() == State.RIGHT){
			if(getPosY() + 1 < world.getWidth()){
			}
			else
    			setPositionY(0);
		}
		else if(getLastState() == State.UP){
			if(getPosX() - speed >= 0){
			}
			else
        		setPositionX( world.getHeight()-1 );
		}
		else if(getLastState() == State.DOWN){
			if(getPosX() + speed < world.getHeight()){
			}
			else
        		setPositionX(0);
		}
	}
	
}
