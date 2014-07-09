package com.example.mysnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.example.mysnake.SnakeGame;
import com.example.mysnake.controller.SnakeController;
import com.example.mysnake.model.Snake;
import com.example.mysnake.model.World;
import com.example.mysnake.view.WorldRenderer;

public class GameScreen implements Screen{

	private SnakeGame mGame;
	
	private WorldRenderer mRenderer;
	private SnakeController mController;
	private World mWorld;
	private Snake mSnake;
	
	@Override
	public void render(float delta) {
		// clear screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mController.update(delta);
		mRenderer.render();
	}

	public GameScreen(SnakeGame mGame) {
		super();
		this.mGame = mGame;
	}

	@Override
	public void resize(int width, int height) {
		mRenderer.setSize(width, height);
	}

	@Override
	public void show() {
		
		mWorld = new World();
		mRenderer = new WorldRenderer(mWorld);
		mController = new SnakeController(mWorld);
		GestureDetector gd = new GestureDetector(mController);
		Gdx.input.setInputProcessor(gd);
		mSnake = mWorld.getmSnake();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		Gdx.app.debug("LOG", "Game.pause()");
		
	}

	@Override
	public void resume() {
		Gdx.app.debug("LOG", "Game.resume()");
	}

	@Override
	public void dispose() {
		Gdx.app.debug("LOG", "Game.dispose()");
		mRenderer.getSpriteBatch().dispose();
	}

}
