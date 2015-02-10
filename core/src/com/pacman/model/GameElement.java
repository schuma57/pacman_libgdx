package com.pacman.model;

/**
 * World c'est le monde visible, il contient tout !
 * 
 * World est constitué d'un Maze, d'un Pacman et des Ghosts
 * Maze est un tableau a deux dimensions de GameElement
 * 
 * Pacman, Block et Ghost héritent de GameElement
 * 
 * Pacman et Ghost sont des MoveableElement,
 * ils ont un State (une Enum) UP, DOWN, RIGHT ou LEFT
 * c'est la direction dans laquelle ils bougent.
 * 
 * La methode autoMove() incremente leur position X ou Y suivant leur State
 * 
 * La position en X et Y des MoveableElement representent leur position dans Maze[x][y]
 * 
 * Bien sur ils ne peuvent pas bouger sur des Blocs
 * 
 * 
 * La méthode chaseMode() de Ghost, doit intercepter Pacman
 * La méthode noChaseMode() se déplace aléatoirement
 * 
 * La méthode ghostMove() sera Override par chaque type de fantome et
 * appelera l'un ou l'autre des modes.
 * 
 * 
 * Les MoveableElement ne peuvent tourner que sur une intersection
 * 
 *
 */

public abstract class GameElement {
	protected float posX;
	protected float posY;
	
	public GameElement(float x, float y){
		posX = x;
		posY = y;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public String getName(){
		return this.getClass().toString();
	}
}
