package com.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private SpriteBatch sBatch;
	private Sprite spritePacman;
	private Sprite spriteGhost[];
	
	private float boxSizeX;
	private float boxSizeY;
	private World world;
	private Pacman pacman;
	private BitmapFont font;
	
	private PacmanFactory pacmanFactory;

	public WorldRenderer(World world){
		this.world = world;
		pacman = world.getPacman();
		pacmanFactory = new PacmanFactory();
		spriteGhost = new Sprite[4];
		
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
		for(i = 0 ; i < 4; i++)
			spriteGhost[i].draw(sBatch);
		
		displayScore();
		spritePacman.draw(sBatch);
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
		if(ghost.isAfraid())
			spriteGhost[i] = new Sprite(TextureFactory.getInstance().getTexture(ghost.getLife().toString()));
		else if(ghost.isDeath())
			spriteGhost[i] = new Sprite(TextureFactory.getInstance().getTexture(ghost.getLife().toString()));
		else
			spriteGhost[i] = new Sprite(TextureFactory.getInstance().getTexture(ghost.getName()));
			
		spriteGhost[i].setOriginCenter();
		
		spriteGhost[i].setSize(boxSizeX-2, boxSizeY);
		
		spriteGhost[i].setOriginCenter();
        spriteGhost[i].setPosition(
        		ghost.getPosY() * boxSizeX,
				(world.getHeight() - ghost.getPosX() -1) * boxSizeY
        );
        
        spriteGhost[i].setOriginCenter();
        
        /*if(ghost.getLastState() == State.RIGHT)
        	spriteGhost[i].rotate(180);*/
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
		if(world.getNbPoints() > 0)
			world.subtractPoints(1);
				
		pacman.autoMove();
		testPellet();
		
		for(Ghost g : world.getListGhosts()){
			g.ghostMove();
			//g.autoMove();
		}
		//System.out.println("State de jaune = " +world.getListGhosts().get(3).getState());
		//System.out.println("State de rouge = " +world.getListGhosts().get(0).getState());
		
		testDeath();
		testWin();
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
		font.draw(sBatch, "SCORE", world.getWidth()*boxSizeX, world.getHeight()*boxSizeY);
		font.draw(sBatch, ""+(world.getNbPoints()),
				world.getWidth()*boxSizeX,
				world.getHeight()*boxSizeY -boxSizeY);
	}
	
	private void testDeath(){
		for(Ghost g : world.getListGhosts()){
			if(g.getLife() == GhostState.NORMAL){
				if(g.getPosX() == pacman.getPosX() && g.getPosY() == pacman.getPosY())
					world.death();
			}
		}
		
		/*for(int i = 0 ; i < 4 ; i++)
			if(spritePacman.getBoundingRectangle().overlaps(spriteGhost[i].getBoundingRectangle()))
				world.death();*/
	}
	
	private void testWin(){
		if(world.getMaze().getNbPellets() <= 0)
			world.winGame();
	}
}
