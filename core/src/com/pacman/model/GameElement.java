package com.pacman.model;

/**
 * World c'est le monde visible, il contient tout !
 * 
 * World est constitu� d'un Maze, d'un Pacman et des Ghosts
 * Maze est un tableau a deux dimensions de GameElement
 * 
 * Pacman, Block et Ghost h�ritent de GameElement
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
 * La m�thode chaseMode() de Ghost, doit intercepter Pacman
 * La m�thode noChaseMode() se d�place al�atoirement
 * 
 * La m�thode ghostMove() sera Override par chaque type de fantome et
 * appelera l'un ou l'autre des modes.
 * 
 * 
 * Les MoveableElement ne peuvent tourner que sur une intersection
 * 
 *
 */

public abstract class GameElement {
	protected Position position;
	
	public GameElement(int x, int y){
		this(new Position(x,y));
	}
	
	public GameElement(Position pos){
		position = pos;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public int getPosX(){
		return position.getX();
	}
	
	public int getPosY(){
		return position.getY();
	}
	
	public String getName(){
		return this.getClass().toString();
	}
}
