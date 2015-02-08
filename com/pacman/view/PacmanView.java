package com.pacman.view;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.model.State;

public class PacmanView{
	public final float SPEED;
	
	private Vector2 position;
	protected Vector2 velocity;
	private float height;
	private float width;
	private State state;
	private State lastState;
	private Rectangle bounds;
	protected float rotation;
	
	public PacmanView(Vector2 pos, float width, float height){
		position = pos;
		SPEED = 0.175f;
		this.height = height;
		this.width = width;
		bounds = new Rectangle(position.x, position.y, width, height);
		velocity = new Vector2(0, 0);
		
		state = State.FRONT;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public State getState() {
		return state;
	}	
		
	public void setState(State state) {
		this.state = state;
		lastState = this.state;
	}
		
	public State getLastState(){
		return lastState;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
		
	public Rectangle getBounds() {
		return bounds;
	}
		
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
