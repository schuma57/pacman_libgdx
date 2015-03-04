package com.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.factories.EyesFactory;
import com.pacman.factories.GhostsFactory;
import com.pacman.factories.PacmanFactory;
import com.pacman.factories.SoundFactory;
import com.pacman.factories.TextureFactory;
import com.pacman.model.Bonus;
import com.pacman.model.GameElement;
import com.pacman.model.MoveableElement;
import com.pacman.model.Pacman;
import com.pacman.model.State;
import com.pacman.model.SuperPellet;
import com.pacman.model.World;
import com.pacman.model.ghosts.Ghost;
import com.pacman.model.ghosts.GhostState;

public class WorldRenderer {
	private final int MALUS_POINTS = 1;
	private final int NBGHOSTS = 4;
	private SpriteBatch sBatch;
	private Sprite spritePacman;
	private Sprite spriteGhost[];
	
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	private Pacman pacman;
	private BitmapFont font;
	
	private PacmanFactory pacmanFactory;
	private GhostsFactory ghostsFactory;

	public WorldRenderer(World world){
		this.world = world;
		pacman = world.getPacman();
		pacmanFactory = new PacmanFactory();
		ghostsFactory = new GhostsFactory();
		spriteGhost = new Sprite[NBGHOSTS];
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		sBatch = new SpriteBatch();
		Gdx.input.setInputProcessor( world.getListener());                                                                                      
	}
	
	public void render(float delta){	
        createPacman();
        int i = 0;
		for(Ghost ghost : world.getListGhosts()){
        	createGhost(ghost, i++);
		}
        
        update();
		sBatch.begin();
		
		for(GameElement element : world){
			renderElement(element);
		}
		
		renderPacmanAndGhosts();
		renderEyesGhosts();
		displayScore();
		
		sBatch.end();
	}
	
	private void createPacman(){
		spritePacman = new Sprite(pacmanFactory.getCurrentFrame());
		spritePacman.setOriginCenter();
		
		spritePacman.setSize(boxSizeX-2, boxSizeY);
		
		spritePacman.setOriginCenter();
        spritePacman.setPosition(
        		pacman.getPosY() * boxSizeX,
				(world.getHeight() - pacman.getPosX() -1) * boxSizeY
        );
        
        spritePacman.setOriginCenter();
        
        if(pacman.getLastState() == State.RIGHT)
        	spritePacman.rotate(180);
        else if(pacman.getLastState() == State.DOWN)
        	spritePacman.rotate(90);
        else if(pacman.getLastState() == State.UP)
        	spritePacman.rotate(-90); 
	}
	
	private void createGhost(Ghost ghost, int i){
		if(ghost.isAfraid()){
			spriteGhost[i] = new Sprite(ghostsFactory.getCurrentFrameAfraid());
			if(System.currentTimeMillis() - ghost.getTimeToAfraid() > 6000){
				if( System.currentTimeMillis() % 8 == 0 )
					spriteGhost[i] = new Sprite(TextureFactory.getInstance().getTexture("WHITE"));
			}
		}
		else if(ghost.isDeath())
			spriteGhost[i] = null;
		else
			spriteGhost[i] = new Sprite(ghostsFactory.getCurrenFrameGhost(ghost.getClass()));
			
		if(spriteGhost[i] != null){
			spriteGhost[i].setOriginCenter();
			spriteGhost[i].setSize(boxSizeX-2, boxSizeY);
			spriteGhost[i].setOriginCenter();
	        spriteGhost[i].setPosition(
	        		ghost.getPosY() * boxSizeX,
					(world.getHeight() - ghost.getPosX() -1) * boxSizeY
	        );
	        
	        spriteGhost[i].setOriginCenter(); 
		}
	}
	
	
	private void renderElement(GameElement element ){
		if( !(element instanceof MoveableElement) ){
			sBatch.draw(
					TextureFactory.getInstance().getTexture(element.getName()),
					element.getPosY() * boxSizeX,
					(world.getHeight() - element.getPosX() -1) * boxSizeY,
					boxSizeX,
					boxSizeY
			);
		}
	}
	
	private void renderPacmanAndGhosts(){
		for(int i = 0 ; i < NBGHOSTS; i++)
			if(spriteGhost[i] != null)
				spriteGhost[i].draw(sBatch);
		
		spritePacman.draw(sBatch);
	}
	
	private void renderEyesGhosts(){
		for(Ghost ghost : world.getListGhosts()){
			sBatch.draw(
					EyesFactory.getInstance().getEyes(ghost.getLastState()),
					(ghost.getPosY() * boxSizeX) +(boxSizeX*0.125f),
					((world.getHeight() - ghost.getPosX() -1) * boxSizeY) +boxSizeY*0.125f,
					boxSizeX*0.75f,
					boxSizeY*0.75f
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
	
	private void update(){
		if(world.getNbPoints() > 0)
			world.subtractPoints(MALUS_POINTS);
				
		pacman.autoMove();
		testPellet();
		
		for(Ghost g : world.getListGhosts())
			g.ghostMove();
				
		testIsDeath();
		testHasWin();
	}
	
	private void testPellet(){
		GameElement ge =
				world.getMaze().getElement(pacman.getPosX(), pacman.getPosY());
			if(ge instanceof Bonus)
				eatPellet((Bonus)ge);
	}
	
	private void eatPellet(Bonus pellet){
		if(pellet instanceof SuperPellet)
			world.ghostsAreAfraid();
		
		SoundFactory.getInstance().getSound("chomp").play();
		world.addPoints(pellet.getPoints());
		world.getMaze().removeElement(pellet.getPosX(), pellet.getPosY());;
	}
	
	private void displayScore(){
		font.draw(sBatch, "SCORE", world.getWidth()*boxSizeX, world.getHeight()*boxSizeY);
		font.draw(sBatch, ""+(world.getNbPoints()),
				world.getWidth()*boxSizeX,
				world.getHeight()*boxSizeY -boxSizeY);
	}
	
	private void testIsDeath(){
		for(Ghost g : world.getListGhosts()){
			if(g.getPosX() == pacman.getPosX() && g.getPosY() == pacman.getPosY()){
				if(g.isNormal())
					world.death();
				else if(g.isAfraid()){
					g.setLife(GhostState.DEATH);
					SoundFactory.getInstance().getSound("eatGhost").play();
				}
			}
		}
	}
	
	private void testHasWin(){
		if(world.getMaze().getNbPellets() <= 0)
			world.winGame();
	}
}
