package com.pacman.model;

public enum State {
	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right"),
	FRONT("Front");
	
	private String text;
	
	State(String t){
		text = t;
	}
	
	public String toString(){
		return text;
	}
}
