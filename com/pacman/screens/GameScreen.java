package com.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pacman.factories.TextureFactory;
import com.pacman.model.World;
import com.pacman.view.WorldRenderer;

public class GameScreen implements Screen{
	private World world;
	private WorldRenderer renderer;
	private OrthographicCamera camera;
	
	public GameScreen(){
		world = new World();
		renderer = new WorldRenderer(world);
		camera = new OrthographicCamera();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		renderer.getBatch().setProjectionMatrix(camera.combined);
		renderer.render(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
		
		camera.setToOrtho(false, width, height);
	    camera.position.set (width / 2, height / 2, 0);
	    camera.update();
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
	public void resume() {
		// empty
	}

	@Override
	public void show() {
		TextureFactory.reset();
	}
}
