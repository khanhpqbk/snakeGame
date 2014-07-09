package com.example.mysnake.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemReverse extends Item {
	public ItemReverse() {
		super();
		mTexture = new Texture(Gdx.files.internal("data/images/item_reverse.jpg"));
	}
}
