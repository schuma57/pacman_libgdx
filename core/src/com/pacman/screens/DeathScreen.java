package com.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pacman.factories.SoundFactory;
import com.pacman.factories.TextureFactory;
import com.pacman.model.Pacman;

public class DeathScreen implements Screen{
	private SpriteBatch sBatch;
	private TextButton button;
	private Skin skin;
	private BitmapFont font;
	private Stage stage;
	private Sound sound;
	private boolean play = false;
	
	public DeathScreen(){
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		stage.act(delta);
		
		sBatch.begin();
		if(!play){
			sound.play();
			play = true;
		}
		stage.draw();
		sBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
			stage = new Stage();
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("logo");
		style.down = skin.getDrawable("logo");
		style.font = font;
		
		button = new TextButton("Vous etes mort", style);
		button.setWidth(300);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);
		
		stage.addActor(button);
	}
	
	@Override
	public void dispose() {
		sBatch.dispose();
		skin.dispose();
		font.dispose();
		stage.dispose();
		sound.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// empty
	}

	@Override
	public void resume() {
		//empty
	}

	@Override
	public void show() {
		sBatch = new SpriteBatch();
		skin = new Skin();
		skin.add("logo", TextureFactory.getInstance().getTexture(Pacman.class.toString()));
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(3);
		
		sound = SoundFactory.getInstance().getSound("death");
	}

}
