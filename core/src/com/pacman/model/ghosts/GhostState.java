package com.pacman.model.ghosts;

public enum GhostState {
	NORMAL("NORMAL"),
	AFRAID("AFRAID"),
	DEATH("DEATH");
	
	private String text;
	
	GhostState(String t){
		text = t;
	}
	
	public String toString(){
		return text;
	}
}
