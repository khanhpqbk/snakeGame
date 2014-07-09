package com.example.mysnakeandroid;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.mysnake.SnakeGame;

public class SnakeActivity extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snake);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		
		initialize(new SnakeGame(), cfg);
		
		
	}

	

}
