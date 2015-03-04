package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze implements Iterable<GameElement>{
	private final int W = 	0; //WALL
	private final int H = 	9; //HOUSE
	private final int D = 	3; //DOOR
	private final int E = 	1; //EMPTY
	private final int S = 	5; //SUPER_PELLET
	private final int I = 	2; //INTERSECTION
	
	private int[][] tableMaze1 = new int[][]{
		    {W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W},
		    {W, I, E, E, E, E, I, E, E, E, E, E, I, W, W, I, E, E, E, E, E, I, E, E, E, E, I, W},
		    {W, E, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, E, W},
		    {W, S, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, S, W},
		    {W, E, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, E, W},
		    {W, I, E, E, E, E, I, E, E, I, E, E, I, E, E, I, E, E, I, E, E, I, E, E, E, E, I, W},
		    {W, E, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, E, W},
		    {W, E, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, E, W},
		    {W, I, E, E, E, E, I, W, W, I, E, E, I, W, W, I, E, E, I, W, W, I, E, E, E, E, I, W},
		    {W, W, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, I, E, E, I, I, I, I, E, E, I, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, E, W, W, W, D, D, W, W, W, E, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, E, W, H, H, H, H, H, H, W, E, W, W, E, W, W, W, W, W, W},
		    {E, E, E, E, E, E, I, E, E, I, W, H, H, H, H, H, H, W, I, E, E, I, E, E, E, E, E, E},
		    {W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, I, E, E, E, E, E, E, E, E, I, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W},
		    {W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W},
		    {W, I, E, E, E, E, I, E, E, I, E, E, I, W, W, I, E, E, I, E, E, I, E, E, E, E, I, W},
		    {W, E, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, E, W},
		    {W, S, W, W, W, W, E, W, W, W, W, W, E, W, W, E, W, W, W, W, W, E, W, W, W, W, S, W},
		    {W, I, E, I, W, W, I, E, E, I, E, E, I, E, E, I, E, E, I, E, E, I, W, W, I, E, I, W},
		    {W, W, W, E, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, E, W, W, W},
		    {W, W, W, E, W, W, E, W, W, E, W, W, W, W, W, W, W, W, E, W, W, E, W, W, E, W, W, W},
		    {W, I, E, I, E, E, I, W, W, I, E, E, I, W, W, I, E, E, I, W, W, I, E, E, I, E, I, W},
		    {W, E, W, W, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, W, W, E, W},
		    {W, E, W, W, W, W, W, W, W, W, W, W, E, W, W, E, W, W, W, W, W, W, W, W, W, W, E, W},
		    {W, I, E, E, E, E, E, E, E, E, E, E, I, E, E, I, E, E, E, E, E, E, E, E, E, E, I, W},
		    {W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W}
	};
	
	private GameElement[][] tableMaze2;
	private boolean[][] visited;
	private int height;
	private int width;
	private List<Bonus> listPellets;
	
	public Maze(){
		height = tableMaze1.length;
		width  = tableMaze1[0].length;
		tableMaze2 = new GameElement[height][width];
		visited = new boolean[height][width];
		listPellets = new ArrayList<Bonus>();
		createMaze();
	}
	
	private void createMaze(){
		int i,j = 0;
      	for( i = 0 ; i < height ; i++ ){
      		for( j = 0 ; j < width ; j++){
      			if(tableMaze1[i][j] == 0)
      				tableMaze2[i][j] = new Block(i, j);
      			else{
      				if(tableMaze1[i][j] == S)
      					tableMaze2[i][j] = new SuperPellet(i, j);
      				else if(tableMaze1[i][j] != H && tableMaze1[i][j] != D)
      					tableMaze2[i][j] = new Pellet(i, j);
      			}
      		}
      	}
    }

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}

	public List<Bonus> getListPellets(){
		return listPellets;
	}
	
	public GameElement getElement(int x, int y){
		return tableMaze2[x][y];
	}
	
	public void removeElement(int x, int y){
		if(tableMaze2[x][y] instanceof Bonus)
			tableMaze2[x][y] = null;
	}
	
	public void removePellet(Bonus pellet){
		if(pellet != null && listPellets.contains(pellet))
			listPellets.remove(pellet);
	}
	
	public int getNbPellets(){
		int nb = 0;
		for(GameElement[] tab : tableMaze2){
			for(GameElement element : tab){
				if(element instanceof Bonus)
					nb++;
			}
		}
		//return 0;
		return nb;
	}
	
	public boolean isIntersection(int x, int y){
		return tableMaze1[x][y] == I;
	}
	
	public boolean isInTheHouse(int x, int y){
		return tableMaze1[x][y] == D;
	}
	
	public void clearVisited() {
		for (int x=0;x< height;x++) {
			for (int y=0;y< width ; y++) {
				visited[x][y] = false;
			}
		}
	}
	
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		List<GameElement> list = new ArrayList<GameElement>();
		list.addAll(listPellets);
		int i,j;
		for(i = 0 ; i < height ; i++){
			for(j = 0 ; j < width ; j++){
				if(tableMaze2[i][j] != null)
					list.add(tableMaze2[i][j]);
			}
		}
		return list.iterator();
	}
}
