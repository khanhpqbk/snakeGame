package com.example.mysnake.model;

import com.badlogic.gdx.math.Vector2;

public class Coord {
	private Vector2 mPosition = new Vector2();
//	private Rectangle mBound = new Rectangle();
//	public static final float SIZE = 1F;
	
	
	
	public Coord(Vector2 position) {
		this.mPosition = position;
//		this.mBound.x = SIZE;
//		this.mBound.y = SIZE;
		
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

	
	
	/**
	 * equals dua. tren tieu chi mPosition
	 * */
	public boolean equals(Block block) {
		if(block.getmPosition().equals(this.mPosition))
			return true;
		return false;
	}
	
	public boolean equals(Coord coord) {
		if(coord.getmPosition().equals(this.mPosition))
			return true;
		return false;
	}
	
	public boolean equals(Vector2 position) {
		if(this.mPosition.equals(position))
			return true;
		return false;
	}
	
}
