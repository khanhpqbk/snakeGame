package com.example.mysnake.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.mysnake.model.Block;
import com.example.mysnake.model.Coord;
import com.example.mysnake.model.Item;
import com.example.mysnake.model.ItemApple;
import com.example.mysnake.model.ItemInvi;
import com.example.mysnake.model.ItemReverse;
import com.example.mysnake.model.Snake;
import com.example.mysnake.model.Snake.Direction;
import com.example.mysnake.model.Snake.OnHeadChangeListener;
import com.example.mysnake.model.Snake.OnStateSetListener;
//import com.example.mysnake.model.Snake.OnStateSetListener;
import com.example.mysnake.model.Snake.State;
import com.example.mysnake.model.Timer;
import com.example.mysnake.model.World;
import com.example.mysnake.model.World.Mode;


public class SnakeController implements GestureListener{
	
	private Snake mSnake;
	private World mWorld;
	
	private Timer timerInvi;
	private Timer timerReverse;
	private Timer timerItemInvi;
	private Timer timerItemRev; 
	
	private Texture mTrunkTextureInvi;
	private Texture mTrunkTextureNormal;
	private Texture mTrunkTextureRev;
	
	public SnakeController(World world) {
		this.mWorld = world;
		this.mSnake = world.getmSnake();
		
		timerItemInvi = new Timer();
		timerInvi = new Timer();
		timerReverse = new Timer();
		timerItemRev = new Timer();
		
		mTrunkTextureInvi = new Texture(Gdx.files.internal("data/images/snake_trunk_invi.png"));
		mTrunkTextureNormal = new Texture(Gdx.files.internal("data/images/snake_trunk_normal.jpg"));
		mTrunkTextureRev = new Texture(Gdx.files.internal("data/images/item_reverse.jpg"));
	}
	
	public void update(float delta) {
		
		// update timer also
		//TODO: update kieu cui`
		timerInvi.update(delta);
		timerItemInvi.update(delta);
		timerReverse.update(delta);
		timerItemRev.update(delta);
		
		if(timerInvi.getmTime() > Timer.MAX_INVI) {
			// reset timer
			timerInvi.reset();
			
			if(mSnake.getmState() == State.INVI ) {
				mSnake.setmState(State.NORMAL);
			}
			
		}
		
		if(timerItemInvi.getmTime() > Timer.MAX_ITEM_INVI) {
			// reset timer
			timerItemInvi.reset();
			mWorld.getmItems().add(new ItemInvi());	
		}
		
		if(timerReverse.getmTime() > Timer.MAX_REVERSE) {
			timerReverse.reset();
			
			if(mSnake.getmState() == State.REVERSE) {
				mSnake.setmState(State.NORMAL);
			}
		}
		
		if(timerItemRev.getmTime() > Timer.MAX_ITEM_REV) {
			timerItemRev.reset();
			mWorld.getmItems().add(new ItemReverse());
		}
		
		// onstateset
		// register observers (listener)
		mSnake.setOnStateSetListener(new OnStateSetListener() {
			
			@Override
			public void onStateSet(State state) {
				if(state == State.NORMAL) {
					mSnake.setmTrunkTexture(mTrunkTextureNormal);
					
				} else if (state == State.INVI) {
					mSnake.setmTrunkTexture(mTrunkTextureInvi);
					timerInvi.reset();
					
				} else if(state == State.REVERSE) {
					timerReverse.reset();
					mSnake.setmTrunkTexture(mTrunkTextureRev);
				}
			}
		});
		
		// onheadChange
		// register observers (listener)
		mSnake.setOnHeadChangeListener(new OnHeadChangeListener() {
			
			
			@Override
			public void onHeadChange(Coord oldHead, Coord newHead) {
				
				// collision detection
				if (mSnake.getmState() != State.INVI) {
					collisionWithBlocks(newHead);
					collisionSelf(newHead);
					
				}
				
				// item eaten
				for(Item item: mWorld.getmItems()) {
					if(newHead.equals(item.getmPosition())) {
						mSnake.onEatItem(item);
						mWorld.getmItems().removeValue(item, false);
						if(item instanceof ItemApple) 
							mWorld.getmItems().add(new ItemApple());
						if(item instanceof ItemInvi) 
							timerItemInvi.reset();
					}
				}
				
			}
		});
		
		// run snake only in Mode.RUNNING
		if (mWorld.getmMode() == Mode.RUNNING) {
			mSnake.update(delta);
		}
		
		// restart game if LOSE
		// restart kieu? bua.
		// TODO: nen thay doi kieu restart nay
		if(mWorld.getmMode() == Mode.LOSE) {
			mWorld.setmSnake(new Snake());
			
			mWorld.setmMode(Mode.READY);
			
			mSnake = mWorld.getmSnake();
		}

				
	}
	
	protected void collisionSelf(Coord newHead) {
		for (Coord item : mSnake.getmSnakeTrails()) {
			if (newHead.equals(item)) {
				mWorld.setmMode(Mode.LOSE);
				return;
			}
		}
	}

	protected void collisionWithBlocks(Coord newHead) {
		Array<Block> blocks = mWorld.getmBlocks();
		for(Block item: blocks) {
			if(newHead.equals(item)) {
				mWorld.setmMode(Mode.LOSE);
				return;
			}
		}
	}


		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			return false;
		}

		// tap to start playing game
		@Override
		public boolean tap(float x, float y, int count, int button) {
			if(mWorld.getmMode()== Mode.READY) 
				mWorld.setmMode(Mode.RUNNING);
			return true;
		}

		@Override
		public boolean longPress(float x, float y) {
			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			if (Math.abs(velocityX) > Math.abs(velocityY)) {
				if (velocityX > 0) {
						mSnake.setmDirection(Direction.EAST);
				} else{
						mSnake.setmDirection(Direction.WEST);
				} 
			} else {

				if (velocityY > 0) {
						mSnake.setmDirection(Direction.SOUTH);
				} else {
						mSnake.setmDirection(Direction.NORTH);
				}
			}
			return true;
		}

		@Override
		public boolean pan(float x, float y, float deltaX, float deltaY) {
			return false;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			return false;
		}

		@Override
		public boolean zoom(float initialDistance, float distance) {
			return false;
		}

		@Override
		public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
				Vector2 pointer1, Vector2 pointer2) {
			return false;
		}
		
}
