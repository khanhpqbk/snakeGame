package com.example.mysnake.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemInvi extends Item{
	public ItemInvi() {
		super();
		mTexture = new Texture(Gdx.files.internal("data/images/item_invi.jpg"));
	}
}
