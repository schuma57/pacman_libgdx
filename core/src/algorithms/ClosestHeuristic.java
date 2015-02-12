package algorithms;

import com.pacman.model.Maze;
import com.pacman.model.ghosts.Ghost;

public class ClosestHeuristic {

	public float getCost(int px, int py, int tx, int ty) {		
		float deltaX = tx - px;
		float deltaY = ty - py;
		
		float result = (float) (Math.sqrt((deltaX*deltaX)+(deltaY*deltaY)));
		
		return result;
	}
}
