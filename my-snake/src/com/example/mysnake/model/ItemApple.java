package com.example.mysnake.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemApple extends Item{
	
	public ItemApple() {
		super();
		mTexture = new Texture(Gdx.files.internal("data/images/item_apple.jpg"));
	}

}
