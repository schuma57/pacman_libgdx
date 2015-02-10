package com.pacman.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	private static final List<State> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
	
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static State randomState()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
