package com.example.mysnake.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.mysnake.view.WorldRenderer;

public class World {
	private Snake mSnake;
	private Array<Block> mBlocks = new Array<>();
	private Array<Item> mItems = new Array<>();
	
	public enum Mode {
		RUNNING,
		PAUSE,
		READY,
		LOSE
	}
	private Mode mMode;
	
	
	public World() {
		createWorld1();
		mMode = Mode.READY;
	}
	
	
//	private void createDemoWorld() {
//		mSnake = new Snake();
//		
//		for(int i=0; i<WorldRenderer.CAMERA_WIDTH; i++) {
//			mBlocks.add(new Block(new Vector2(i, 0)));
//			mBlocks.add(new Block(new Vector2(i, WorldRenderer.CAMERA_HEIGHT-1)));
//		}
//		for(int i=1; i<WorldRenderer.CAMERA_HEIGHT-1; i++) {
//			mBlocks.add(new Block(new Vector2(0, i)));
//			mBlocks.add(new Block(new Vector2(WorldRenderer.CAMERA_WIDTH-1, i)));
//		}
//		
//		
//		
//	}
	
	/**
	 * world level 1 =))
	 * */
	private void createWorld1() {
		mSnake = new Snake();
		
		for(int i=0; i<WorldRenderer.CAMERA_WIDTH; i++) {
			mBlocks.add(new Block(new Vector2(i, 0)));
			mBlocks.add(new Block(new Vector2(i, WorldRenderer.CAMERA_HEIGHT-1)));
		}
		for(int i=1; i<WorldRenderer.CAMERA_HEIGHT-1; i++) {
			mBlocks.add(new Block(new Vector2(0, i)));
			mBlocks.add(new Block(new Vector2(WorldRenderer.CAMERA_WIDTH-1, i)));
		}
		
		// BLOCKS o gan` chinh giua world
		for(int i=9; i<12; i++)
			mBlocks.add(new Block(new Vector2(i, WorldRenderer.CAMERA_WIDTH/2)));
		
		// xuat hien item random
		// 2 apples va 1 invi  va 1 reverse la default
		mItems.add(new ItemApple());
		mItems.add(new ItemApple());
		
		mItems.add(new ItemInvi());
		mItems.add(new ItemReverse());
	}

	public Snake getmSnake() {
		return mSnake;
	}
	
	public void setmSnake(Snake mSnake) {
		this.mSnake = mSnake;
	}
	public Array<Block> getmBlocks() {
		return mBlocks;
	}
	public void setmBlocks(Array<Block> mBlocks) {
		this.mBlocks = mBlocks;
	}


	public Mode getmMode() {
		return mMode;
	}


	public void setmMode(Mode mode) {
		this.mMode = mode;
	}


	public Array<Item> getmItems() {
		return mItems;
	}


	public void setmItems(Array<Item> mItems) {
		this.mItems = mItems;
	}
	
	
}
