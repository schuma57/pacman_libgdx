package algorithms;

import com.pacman.model.Maze;
import com.pacman.model.ghosts.Ghost;

public class ClosestHeuristic {

	public float getCost(Maze map, Ghost mover, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
