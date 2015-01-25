package com.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.pacman.model.World;
import com.pacman.view.TextureFactory;
import com.pacman.view.WorldRenderer;

public class GameScreen implements Screen{
	private World world;
	private WorldRenderer renderer;
	
	public GameScreen(){
		world = new World();
		renderer = new WorldRenderer(world);
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		renderer.render(delta);
	}
	
	@Override
	public void dispose() {
		// empty
	}

	@Override
	public void hide() {
		// empty
	}

	@Override
	public void pause() {
		// empty
	}

	@Override
	public void resize(int width, int height) {
		renderer.setBoxSizeX( (float)width / (float)world.getWidth());
		renderer.setBoxSizeY( (float)height / (float)world.getHeight());
	}

	@Override
	public void resume() {
		// empty
	}

	@Override
	public void show() {
		TextureFactory.reset();
	}
}
