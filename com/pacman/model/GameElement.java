package com.pacman.model;

import java.util.HashMap;
import java.util.Map;

public abstract class GameElement {
	private float posX;
	private float posY;
	
	public GameElement(float x, float y){
		posX = x;
		posY = y;
	}
	
	@SuppressWarnings("serial")
	public Map<String, Float> getPosition(){
		return new HashMap<String, Float>(){{put("x", posX);put("y", posY);}};
	}
	
	public String getName(){
		return this.getClass().toString();
	}
}
