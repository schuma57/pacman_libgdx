package com.pacman.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.factories.TextureFactory;
import com.pacman.model.Block;
import com.pacman.model.Bonus;
import com.pacman.model.GameElement;
import com.pacman.model.Ghost;
import com.pacman.model.MoveableElement;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldRenderer {
	private SpriteBatch sBatch;
	private Sprite sprite;
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	private Pacman pacman;
	private int moveCount = 0;
	private static final float moveSpeed = 0.5f;
	
	private Animation anim;
	private Texture tex;
	private TextureRegion[] walk;
	private TextureRegion currentFrame;
	private float stateTime;
	
	public WorldRenderer(World world){
		this.world = world;
		pacman = world.getPacman();
		
		sBatch = new SpriteBatch();
		Gdx.input.setInputProcessor( world.getListener());
		
		tex = TextureFactory.getInstance().getTexture("Pacman");
		TextureRegion[][] temp = TextureRegion.split(tex, tex.getWidth()/4, tex.getHeight());
		walk = new TextureRegion[4];
		int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                walk[index++] = temp[i][j];
            }
        }
        
        anim = new Animation(0.125f, walk);
        stateTime = 0f;                                                                                       
	}
	
	public void render(float delta){	
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = anim.getKeyFrame(stateTime, true);
        createPacman();
        
        update();
		sBatch.begin();
		testDeath();
		testWin();
		
		for(GameElement element : world){
			renderElement(element);
		}                                                                      
			
		sprite.draw(sBatch);
		sBatch.end();
	}
	
	private void createPacman(){
		sprite = new Sprite(currentFrame);
		sprite.setOriginCenter();
		
		sprite.setSize(boxSizeX-2, boxSizeY);
		
		sprite.setOriginCenter();
        sprite.setPosition(
        		pacman.getPosY() * boxSizeX,
				(world.getHeight() - pacman.getPosX() -1) * boxSizeY
        );
        
        sprite.setOriginCenter();
        
        if(pacman.getLastState() == State.RIGHT)
        	sprite.rotate(180);
        else if(pacman.getLastState() == State.DOWN)
        	sprite.rotate(90);
        else if(pacman.getLastState() == State.UP)
        	sprite.rotate(-90); 
	}
	
	private void renderElement(GameElement element ){
		if( !(element instanceof Pacman) ){
			sBatch.draw(
					TextureFactory.getInstance().getTexture(element.getName()),
					element.getPosY() * boxSizeX,
					(world.getHeight() - element.getPosX() -1) * boxSizeY,
					boxSizeX,
					boxSizeY
			);
		}
	}
	
	public void resize(float width, float height){
		setBoxSizeX( (float)width / (float)world.getWidth());
		setBoxSizeY( (float)height / (float)world.getHeight());
	}
	
	private void setBoxSizeX(float x){
		boxSizeX = x;
	}
	
	private void setBoxSizeY(float y){
		boxSizeY = y;
	}
	
	public SpriteBatch getBatch(){
		return sBatch;
	}
	
	public void update(){
		moveCount++;
		autoMove(pacman);
		autoMove(world.getListGhosts().get(1));
		testCollision(world.getListGhosts().get(1));
		if(moveCount % 2 == 0){
			testCollision(pacman);
			
			//eatBonus();
			displayScore();
		}
	}
	
	
	private void testCollision(MoveableElement element){
		float posXFloat = element.getPosX();
		float posYFloat = element.getPosY();
	
		if(element.getLastState() == State.LEFT){
			if(element.getPosY() - moveSpeed >= 0){	
				GameElement ge =
						world.getMaze().getElement((int)posXFloat, (int) Math.floor(posYFloat));
				if(ge instanceof Block){
					element.setPositionY( element.getPosY()+ moveSpeed );
				}
				else if(ge instanceof Bonus){
					if(element instanceof Pacman)
						eatPellet((Bonus)ge);
				}
			}
			else
        		element.setPositionY( world.getWidth()-1);
		}
		else if(element.getLastState() == State.RIGHT){
			if(element.getPosY() +moveSpeed < world.getWidth()){
				GameElement ge =
						world.getMaze().getElement((int)posXFloat, (int) Math.round(posYFloat));
				if(ge instanceof Block){
					element.setPositionY( element.getPosY() - moveSpeed );
				}
				else if(ge instanceof Bonus){
					if(element instanceof Pacman)
						eatPellet((Bonus)ge);
				}
			}
			else
    			element.setPositionY(0);
		}
		else if(element.getLastState() == State.UP){
			if(element.getPosX() -moveSpeed >= 0){
				GameElement ge =
						world.getMaze().getElement((int)Math.floor(posXFloat), (int)posYFloat);
				if(ge instanceof Block){
					element.setPositionX( element.getPosX() + moveSpeed );
				}
				else if(ge instanceof Bonus){
					if(element instanceof Pacman)
						eatPellet((Bonus)ge);
				}
			}
			else
        		element.setPositionX( world.getHeight()-1 );
		}
		else if(element.getLastState() == State.DOWN){
			if(element.getPosX() +moveSpeed < world.getHeight()){
				GameElement ge =
						world.getMaze().getElement((int)Math.round(posXFloat), (int)posYFloat);
				if(ge instanceof Block){
					element.setPositionX( element.getPosX() - moveSpeed );
				}
				else if(ge instanceof Bonus){
					if(element instanceof Pacman)
						eatPellet((Bonus)ge);
				}
			}
			else
        		element.setPositionX(0);
		}
	}
	
	private void autoMove(MoveableElement element) {
		if(world.hasIntersection())
    		element.setLastState(element.getState());
		
		if(moveCount == 8){
			if(element.getLastState() == State.RIGHT){
				element.setPositionY( element.getPosY() + moveSpeed );
			}
			else if(element.getLastState() == State.LEFT){
				element.setPositionY( element.getPosY() - moveSpeed );
			}
			else if(element.getLastState() == State.UP){
				element.setPositionX( element.getPosX() - moveSpeed );
			}
			else if(element.getLastState() == State.DOWN){
				element.setPositionX( element.getPosX() + moveSpeed );
			}
			moveCount = 0;
		}
	}
	
	private void eatPellet(Bonus pellet){
		world.addPoints(pellet.getPoints());
		world.getMaze().removeElement((int)pellet.getPosX(), (int)pellet.getPosY());;
	}
	
	/*private void eatBonus(){
		for(Bonus bonus : world.getMaze().getListPellets()){
			if(bonus.getPosX() == pacView.getPosX() &&
					bonus.getPosY() == pacView.getPosY())
				world.getMaze().removePellet(bonus);
		}
	}*/
	
	private void displayScore(){
		//System.out.println("Le Score = " +world.getNbPoints());
	}
	
	private void testDeath(){
		Pacman pac = world.getPacman();
		List<Ghost> ghosts = world.getListGhosts();
		for(Ghost g : ghosts){
			if(g.getPosX() == pac.getPosX() && g.getPosY() == pac.getPosY())
				world.death();
		}
		
	}
	
	private void testWin(){
		if(world.getMaze().getNbPellets() <= 0)
			world.winGame();
	}
}
