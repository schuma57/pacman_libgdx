package com.pacman.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.adapters.PacmanAdapter;
import com.pacman.factories.TextureFactory;
import com.pacman.model.Block;
import com.pacman.model.Bonus;
import com.pacman.model.GameElement;
import com.pacman.model.Ghost;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.World;

public class WorldRenderer {
	private SpriteBatch sBatch;
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	private Pacman pacman;
	private PacmanAdapter adapter;
	private int moveCount = 0;
	public static final float moveSpeed = 0.5f;
	
	private Animation anim;
	private Texture tex;
	private TextureRegion[] walk;
	private TextureRegion currentFrame;
	float stateTime;
	
	public WorldRenderer(World world){
		this.world = world;
		pacman = world.getPacman();
		//adapter = new PacmanAdapter();
		//pacView = adapter.adaptePacman(world.getPacman(), boxSizeX, boxSizeY);
		
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
		
        update();
		sBatch.begin();
		testDeath();
		testWin();
		for(GameElement element : world){
			renderElement(element);
		}
		/*sBatch.draw(currentFrame,
				pacView.getPosition().y * boxSizeX,
				pacView.getPosition().x * boxSizeY,
				boxSizeX,
				boxSizeY
				);*/             
		/*sBatch.draw(
				TextureFactory.getInstance().getTexture(pacView.getState().toString()),
				pacView.getPosition().y * boxSizeX,
				(world.getHeight() - pacView.getPosition().x -1) * boxSizeY,
				boxSizeX,
				boxSizeY
		);*/
		sBatch.end();
	}
	
	private void renderElement(GameElement element ){
		String textur = null;
		if( !(element instanceof Pacman) )
			textur = element.getName();
		else{
			Pacman pac = (Pacman)element;
			textur = pac.getState().toString();	
		}
		
		sBatch.draw(
				TextureFactory.getInstance().getTexture(textur),
				element.getPosY() * boxSizeX,
				(world.getHeight() - element.getPosX() -1) * boxSizeY,
				boxSizeX,
				boxSizeY
		);
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

	public World getWorld(){
		return world;
	}
	
	public void update(){
		autoMove();
		if(moveCount % 2 == 0){
			testCollision();
			//eatBonus();
			displayScore();
		}
	}
	
	private void testCollision(){
		float posXFloat = pacman.getPosX();
		float posYFloat = pacman.getPosY();
		
		if(pacman.getState() == State.LEFT){
			if(pacman.getPosY() -moveSpeed >= 0){	
				GameElement element =
						world.getMaze().getElement((int)posXFloat, (int) Math.floor(posYFloat));
				if(element instanceof Block){
					pacman.setPositionY( pacman.getPosY()+ moveSpeed );
				}
				else if(element instanceof Bonus)
					eatPellet((Bonus)element);
			}
			else
        		pacman.setPositionY( world.getWidth()-1);
		}
		else if(pacman.getState() == State.RIGHT){
			if(pacman.getPosY() +moveSpeed < world.getWidth()){
				GameElement element =
						world.getMaze().getElement((int)posXFloat, (int) Math.round(posYFloat));
				if(element instanceof Block){
					pacman.setPositionY( pacman.getPosY() - moveSpeed );
				}
				else if(element instanceof Bonus)
					eatPellet((Bonus)element);
			}
			else
    			pacman.setPositionY(0);
		}
		else if(pacman.getState() == State.UP){
			if(pacman.getPosX() -moveSpeed >= 0){
				GameElement element =
						world.getMaze().getElement((int)Math.floor(posXFloat), (int)posYFloat);
				if(element instanceof Block){
					pacman.setPositionX( pacman.getPosX() + moveSpeed );
				}
				else if(element instanceof Bonus)
					eatPellet((Bonus)element);
			}
			else
        		pacman.setPositionX( world.getHeight()-1 );
		}
		else if(pacman.getState() == State.DOWN){
			if(pacman.getPosX() +moveSpeed < world.getHeight()){
				GameElement element =
						world.getMaze().getElement((int)Math.round(posXFloat), (int)posYFloat);
				if(element instanceof Block){
					pacman.setPositionX( pacman.getPosX() - moveSpeed );
				}
				else if(element instanceof Bonus)
					eatPellet((Bonus)element);
			}
			else
        		pacman.setPositionX(0);
		}
	}
	
	private void autoMove() {
		moveCount++;
		if(moveCount == 8){
			if(pacman.getState() == State.RIGHT){
				pacman.setPositionY( pacman.getPosY() + moveSpeed );
			}
			else if(pacman.getState() == State.LEFT){
				pacman.setPositionY( pacman.getPosY() -moveSpeed );
			}
			else if(pacman.getState() == State.UP){
				pacman.setPositionX( pacman.getPosX() - moveSpeed );
			}
			else if(pacman.getState() == State.DOWN){
				pacman.setPositionX( pacman.getPosX() +moveSpeed );
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
		System.out.println("Lescore = " +world.getNbPoints());
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
