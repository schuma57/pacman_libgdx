package com.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze implements Iterable<GameElement>{
	private int[][] tableMaze1 = new int[][]{
		    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		    {0, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 0},
		    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		    {0, 5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 5, 0},
		    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		    {0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
		    {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		    {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		    {0, 2, 1, 1, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 1, 1, 2, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 9, 9, 9, 9, 9, 9, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 9, 9, 9, 9, 9, 9, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		    {0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
		    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		    {0, 5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 5, 0},
		    {0, 2, 1, 2, 0, 0, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 2, 1, 2, 0},
		    {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
		    {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
		    {0, 2, 1, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 1, 2, 0},
		    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
		    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
		    {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0},
		    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	private GameElement[][] tableMaze2;
	private int height;
	private int width;
	private List<Bonus> listPellets;
	
	public Maze(){
		height = tableMaze1.length;
		width  = tableMaze1[0].length;
		tableMaze2 = new GameElement[height][width];
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
      				if(tableMaze1[i][j] == 5)
      					tableMaze2[i][j] = new SuperPellet(i, j);
      				else if(tableMaze1[i][j] != 9 && tableMaze1[i][j] != 3)
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
		//return 0;
		int nb = 0;
		for(GameElement[] tab : tableMaze2){
			for(GameElement element : tab){
				if(element instanceof Bonus)
					nb++;
			}
		}
      	return nb;
	}
	
	public boolean isIntersection(int x, int y){
		return tableMaze1[x][y] == 2;
	}
	
	public boolean isInTheHouse(int x, int y){
		return tableMaze1[x][y] == 9;
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
