package com.example.mysnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.example.mysnake.screens.GameScreen;
import com.example.mysnake.screens.MainScreen;

public class SnakeGame extends Game{

	Screen menuScreen;
	Screen gameScreen;
	
	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		menuScreen = new MainScreen(this);
		
		this.setScreen(menuScreen);
	}

	
}
