package com.pacman.model.ghosts;

import com.pacman.model.Block;
import com.pacman.model.MoveableElement;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public abstract class Ghost extends MoveableElement{
	private Pacman pacman;
	private GhostState life;
	protected float startX;
	protected float startY;
	protected float targetX = 0;
	protected float targetY = 0;
	
	public Ghost(float x, float y, World w) {
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
		posX = startX;
		posY = startY;
		life = GhostState.NORMAL;
		setState(State.UP);
		setLastState(State.FRONT);
	}
	
	protected void chaseMode(){
		//setLastState(calculDirection(getPacman().getPosX(), getPacman().getPosY()));
	}
	
	protected void noChaseMode(){
		if(getWorld().isInTheHouse(this)){
			targetX = 11;
			targetY = 12;
			
			if(getPosY() > targetY)
				setLastState(State.LEFT);
			if(getPosY() < targetY)
				setLastState(State.RIGHT);
			if(getPosX() > targetX )
				setLastState(State.UP);
			if(getPosX() < targetX)
				setLastState(State.DOWN);
		}
		else{
			if(getWorld().hasIntersection(this))
				setLastState(State.randomState());
		}
	}
	
	protected void escapePacman(){
		
	}
	
	/*private State calculDirection(float xPac, float yPac) 
	{
		State[] direction = new State[4] ;
		int cx = (int) (xPac - getPosX()) ;
		int cy = (int) (yPac - getPosY()) ;
		
		//if(life == GhostState.AFRAID)){
		//	cx = -cx ; cy = -cy ;
		//}
		
		
		if ((cx == 0) && (cy == 0)) direction[0] = State.FRONT; //FRONT here
		else{
		  if (Math.abs(cx) > Math.abs(cy))
			{
			 	if (cx > 0) { direction[0] = State.DOWN ; direction[3] = State.UP ; }
			    else { direction[0] = State.UP ; direction[3] = State.DOWN ; }
			 	if (cy > 0) { direction[1] = State.RIGHT ; direction[2] = State.LEFT ; }
			 	else { direction[1] = State.LEFT ; direction[2] = State.RIGHT ; }
			}
		  else
			{
				if (cx > 0) { direction[1] = State.DOWN ; direction[2] = State.UP ; }
			    else { direction[1] = State.UP ; direction[2] = State.DOWN ; }
			 	if (cy > 0) { direction[0] = State.RIGHT ; direction[3] = State.LEFT ; }
			 	else { direction[0] = State.LEFT ; direction[3] = State.RIGHT ; }
			}
		}


		// On determine la direction la plus prioritaire qui ne pointe pas une brique.
		boolean sortie = false ;
		int i = -1 ;
		int new_x = (int)getPosX();
		int new_y = (int)getPosY() ; 
		do
		{
			i++ ;
			if (direction[i] == State.UP)        { new_x = (int)getPosX()-1 ; new_y = (int)getPosY()  ; }
			else if (direction[i] == State.DOWN)    { new_x = (int)getPosX()+1 ; new_y = (int)getPosY()   ; }
			else if (direction[i] == State.LEFT) { new_x = (int) getPosX()   ; new_y = (int)getPosY()-1 ; }
			else if (direction[i] == State.RIGHT) { new_x = (int) getPosX()   ; new_y = (int)getPosY()+1 ; }

			if ( !(getWorld().getMaze().getElement(new_x, new_y) instanceof Block) )
				sortie = true ;
		} while(sortie == false) ;

		if(i>3) System.out.println("Fantome.calculDirection : Cas Impossible...") ;
		return direction[i] ;
	}*/
	
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
}
