package com.example.mysnake.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.example.mysnake.view.WorldRenderer;

public class Item {
//	protected Snake mSnake;
	protected Vector2 mPosition = new Vector2();
	protected Random mRandom = new Random();
	protected Texture mTexture;
	
	/**
	 * default item random trong khoang den man hinh
	 * k chua cac blocks ria`
	 * */
	public Item() {
		mPosition.x = mRandom.nextInt( (int)WorldRenderer.CAMERA_WIDTH -3) + 1;
		mPosition.y = mRandom.nextInt( (int)WorldRenderer.CAMERA_HEIGHT -3) + 1;
	}
	
	public void onItemEaten(){
		
	}

	public Vector2 getmPosition() {
		return mPosition;
	}

	public void setmPosition(Vector2 mPosition) {
		this.mPosition = mPosition;
	}

	public Texture getmTexture() {
		return mTexture;
	}

	public void setmTexture(Texture mTexture) {
		this.mTexture = mTexture;
	}
	
	// tieu chi la mPosition
	public boolean equals(Object obj) {
		Item item = (Item) obj;
		if(mPosition.equals(item.getmPosition()))
			return true;
		return false;
	}
	
}
