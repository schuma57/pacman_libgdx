package com.pacman.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class WorldView {
	/*PacmanView pacman;
	private TiledMap map;
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	
	private Array<Rectangle> tiles = new Array<Rectangle>();
	int Score =0;
	
	public static final float moveSpeed = 0.5f;
	
	int moveCount =0;
	public boolean eatghost = false;
	
	public WorldView() {
		pacman = new PacmanView(new Vector2(13, 7), 1, 1, 0, 5f);
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("data/maps/PacMan.tmx");
		Gdx.input.setInputProcessor(new InputWorldListener(this));
	}
	
	public void update(float dt) {
		autoMove();
		WallCollisionDecetion();
		eatCoins();
		logScore();
	}
	
	private void autoMove() {
		moveCount++;
		if(moveCount == 10){
			if(pacman.getlastState() == State.up){
				pacman.setPosition(new Vector2(pacman.getPosition().x,pacman.getPosition().y+moveSpeed));
			}
			if(pacman.getlastState() == State.Down){
				pacman.setPosition(new Vector2(pacman.getPosition().x,pacman.getPosition().y-moveSpeed));
			}
			if(pacman.getlastState() == State.Left){
				pacman.setPosition(new Vector2(pacman.getPosition().x-moveSpeed,pacman.getPosition().y));
			}
			if(pacman.getlastState() == State.Right){
				pacman.setPosition(new Vector2(pacman.getPosition().x+moveSpeed,pacman.getPosition().y));
			}
			moveCount =0;
		}
	}
	
	private void WallCollisionDecetion() {
		getTiles(pacman.getPosition().x, pacman.getPosition().y, pacman.getPosition().x+pacman.getWidth(), pacman.getPosition().y+ pacman.getHeight(), tiles);
		pacman.update(pacman);
		for(Rectangle tile: tiles) {
			if(pacman.getBounds().overlaps(tile)) {
				Gdx.app.log("collision", "true");
				
				if(pacman.getlastState() == State.up){
					pacman.setPosition(new Vector2(pacman.getPosition().x,pacman.getPosition().y-moveSpeed));
				}
				if(pacman.getlastState() == State.Down){
					pacman.setPosition(new Vector2(pacman.getPosition().x,pacman.getPosition().y+moveSpeed));
				}
				if(pacman.getlastState() == State.Left){
					pacman.setPosition(new Vector2(pacman.getPosition().x+moveSpeed,pacman.getPosition().y));
				}
				if(pacman.getlastState() == State.Right){
					pacman.setPosition(new Vector2(pacman.getPosition().x-moveSpeed,pacman.getPosition().y));
				}
			}
		}
	}
	
	private void logScore() {
		Gdx.app.log("Score ", ""+Score);
	}
	
	private void eatCoins() {
		boolean coinClear = getCollectables(pacman.getPosition().x, pacman.getPosition().y, pacman.getPosition().x+pacman.getWidth(), pacman.getPosition().y+ pacman.getHeight(), tiles);
		
		for(Rectangle tile: tiles) {
			if(pacman.getBounds().overlaps(tile)) {
				Iterator<TiledMapTile> tileset = map.getTileSets().getTileSet("PacMan").iterator();
				StaticTiledMapTile frametile = null;
				while(tileset.hasNext()) {
					TiledMapTile tilee = tileset.next();
					if(tilee.getProperties().containsKey("empty") && tilee.getProperties().get("empty", String.class).equals("true")){
						frametile= (StaticTiledMapTile) tilee;
					}
				}
				
					TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Collectables");
					Cell cell = layer.getCell((int)tile.x, (int)tile.y);
					if(cell.getTile().getProperties().containsKey("coin")&& cell.getTile().getProperties().get("coin",String.class).equals("1")){
						Score++;
					}
					
					if(cell.getTile().getProperties().containsKey("coin")&& cell.getTile().getProperties().get("coin",String.class).equals("10")){
						eatghost = true;
					}
					cell.setTile(frametile);
			}
		}
	}
	
	private void getTiles(float startX, float startY, float endX, float endY, Array<Rectangle> tiles) {
		int startXX = Math.round(startX);
		int startYY = Math.round(startY);
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("Walls");
		rectPool.freeAll(tiles);
		tiles.clear();
		
		for(int y = startYY; y <= endY; y++) {
			for(int x = startXX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}
	
	private boolean getCollectables(float startX, float startY, float endX, float endY, Array<Rectangle> tiles) {
		boolean coinClear = false;
		coinClear = false;
		int startXX = Math.round(startX);
		int startYY = Math.round(startY);
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("Collectables");
		rectPool.freeAll(tiles);
		tiles.clear();
		for(int y = startYY; y <= endY; y++) {
			for(int x = startXX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null) {
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
		return coinClear;
	}
	public Array<Rectangle> getTiles(){
		return tiles;
	}
	public PacmanView getPacMan(){
		return pacman;
	}
	public TiledMap getMap(){
		return map;
	}*/
}

