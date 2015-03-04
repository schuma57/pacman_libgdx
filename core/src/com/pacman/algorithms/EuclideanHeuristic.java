package com.pacman.algorithms;

public class EuclideanHeuristic {

	public float getCost(int px, int py, int tx, int ty) {		
		float deltaX = tx - px;
		float deltaY = ty - py;
		
		float result = (float) (Math.sqrt((deltaX*deltaX)+(deltaY*deltaY)));
		
		return result;
	}
}
