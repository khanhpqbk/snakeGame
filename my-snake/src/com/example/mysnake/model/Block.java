package com.example.mysnake.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Block {
	private Vector2 mPosition = new Vector2();
//	private Rectangle mBound = new Rectangle();
//	public static final float SIZE = 1F;
	private static Texture mTexture;
	
	public Block(Vector2 position) {
		this.mPosition = position;
//		this.mBound.x = SIZE;
//		this.mBound.y = SIZE;
		Block.mTexture = new Texture(Gdx.files.internal("data/images/block.png"));
	}
	
	
	public Vector2 getmPosition() {
		return mPosition;
	}
	
	
	public void setmPosition(Vector2 mPosition) {
		this.mPosition = mPosition;
	}
//	public Rectangle getmBound() {
//		return mBound;
//	}
//	public void setmBound(Rectangle mBound) {
//		this.mBound = mBound;
//	}


	public static Texture getmTexture() {
		return mTexture;
	}


	public static void setmTexture(Texture mTexture) {
		Block.mTexture = mTexture;
	}
	
	
	
	
}
