package com.example.mysnake.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.mysnake.view.WorldRenderer;

public class Snake {
	
	private Texture mHeadTexture;
	private Texture mTrunkTexture;
	
	private Timer mTimer = new Timer();

	public enum State {
		NORMAL,
		INVI, // xuyen tuong`.(blocks)
		REVERSE, // dao? nguoc control
	}
	private State mState = State.NORMAL;
	/**
	 * Current direction the snake is headed.
	 */
	private Direction mDirection = Direction.NORTH;
	public enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

	private float mDelay = 0.4f; // 0.4 s
	
	private int mDurationInvi = 5; // thoi gian invi (5 MOVES on default)

	/**
	 * snake head la mSnakeTrails.get(0);
	 * */
	private Array<Coord> mSnakeTrails = new Array<>();

	public Snake() {
		createDemoSnake();
		mHeadTexture = new Texture(Gdx.files.internal("data/images/snake_head.jpg"));
		mTrunkTexture = new Texture(Gdx.files.internal("data/images/snake_trunk_normal.jpg"));
		mDirection = Direction.NORTH;
		mState = State.NORMAL;
	}

	private void createDemoSnake() {
		for (int i = 15; i > 10; i--)
			mSnakeTrails.add(new Coord(new Vector2(5, i)));
	}
	
	public Array<Coord> newTrails() {
		Array<Coord> trails = new Array<Coord>();
		for (int i = 15; i > 10; i--)
			trails.add(new Coord(new Vector2(5, i)));
		return trails;
	}

	/**
	 * di chuyen co delay. <br>
	 * dua. vao mDirection delay <br>
	 * dua. vao delta time <br>
	 * 
	 * Chi khi mode la RUNNING thi moi chay snake
	 * 
	 * */
	public void update(float delta) {
		mTimer.update(delta);
		if (mTimer.getmTime() > mDelay) {
			move(mDirection);
			mTimer.reset();
		}
	}

	/**
	 * insert vao dau` va remove cuoi <br>
	 * 
	 * co detect truong hop newHead ra khoi man hinh <br>
	 * 
	 * also call onHeadChangeListener
	 * 
	 * */
	public void move(Direction direction) {
		Coord head = mSnakeTrails.get(0);
		Coord newHead = new Coord(new Vector2(1, 1));

		// mDirection = mNextDirection;

		switch (direction) {
		case EAST:
			newHead = new Coord(new Vector2(head.getmPosition().x + 1,
					head.getmPosition().y));
			
			// neu newHead ra khoi man hinh
			if(newHead.getmPosition().x >= WorldRenderer.CAMERA_WIDTH)
				newHead = new Coord(new Vector2( 0,
						newHead.getmPosition().y));
			break;
		case WEST:
			newHead = new Coord(new Vector2(head.getmPosition().x - 1,
					head.getmPosition().y));
			
			// neu newHead ra khoi man hinh
			if(newHead.getmPosition().x < 0)
				newHead = new Coord(new Vector2( WorldRenderer.CAMERA_WIDTH-1,
						newHead.getmPosition().y));
			break;
		case NORTH:
			newHead = new Coord(new Vector2(head.getmPosition().x,
					head.getmPosition().y + 1));

			// neu newHead ra khoi man hinh
			if(newHead.getmPosition().y >= WorldRenderer.CAMERA_HEIGHT)
				newHead = new Coord(new Vector2( newHead.getmPosition().x,
						0));
			break;
		case SOUTH:
			newHead = new Coord(new Vector2(head.getmPosition().x,
					head.getmPosition().y - 1));
			
			// neu newHead ra khoi man hinh
			if(newHead.getmPosition().y < 0)
				newHead = new Coord(new Vector2( newHead.getmPosition().x,
						WorldRenderer.CAMERA_HEIGHT-1));
			break;
		default:
			break;
		}

		
		// //////////////////////
		// collision detection
		// notify-> update observers
		if (mHeadChangeListener != null)
			mHeadChangeListener.onHeadChange(head, newHead);
		// ///////////////////


		mSnakeTrails.insert(0, newHead);
		
		
		
		mSnakeTrails.removeIndex(mSnakeTrails.size - 1);

	}
	
	public void onEatItem(Item item) {
		if(item instanceof ItemApple) {
			
			// cho dai` them duoi ran
			Vector2 pos1 = mSnakeTrails.get(mSnakeTrails.size-1).getmPosition(); // duoi
			Vector2 pos2 = mSnakeTrails.get(mSnakeTrails.size-2).getmPosition(); // ap chot cua duoi
			if(pos1.x == pos2.x){
				if(pos1.y > pos2.y) {
					mSnakeTrails.add(new Coord(new Vector2(pos1.x, pos1.y + 1)));
				} else {
					mSnakeTrails.add(new Coord(new Vector2(pos1.x, pos1.y - 1)));
				}
			} else {
				if(pos1.x > pos2.x) {
					mSnakeTrails.add(new Coord(new Vector2(pos1.x + 1, pos1.y)));
				} else {
					mSnakeTrails.add(new Coord(new Vector2(pos1.x - 1, pos1.y)));
				}
			}
			
			// giam? delay (<=> tang speed)
			mDelay *= 0.9;
			
		} 
		
		else if (item instanceof ItemInvi) {
			setmState(State.INVI);
		} else if(item instanceof ItemReverse) {
			setmState(State.REVERSE);
		}
	}

	public Array<Coord> getmSnakeTrails() {
		return mSnakeTrails;
	}

	public void setmSnakeTrails(Array<Coord> mSnakeTrails) {
		this.mSnakeTrails = mSnakeTrails;
	}

	public Direction getmDirection() {
		return mDirection;
	}

	/**
	 * khong cho phep set direction nguoc lai vs direction hien tai mDirection
	 * */
	public void setmDirection(Direction direction) {
		Direction oldDirection = this.mDirection;
		Direction newDirection = direction;

		this.mDirection = direction;
		
		onmDirectionSet(oldDirection, newDirection);
	}

	private void onmDirectionSet(Direction oldDirection, Direction newDirection) {
		
		// prevent snake from being changed to reverse direction
		if( (oldDirection == Direction.EAST && newDirection == Direction.WEST)
		|| (oldDirection == Direction.WEST && newDirection == Direction.EAST)
		|| (oldDirection == Direction.NORTH && newDirection == Direction.SOUTH)
		|| (oldDirection == Direction.SOUTH && newDirection == Direction.NORTH))
			mDirection = oldDirection;
		
		else {
			// xu li khi state la reverse
			if(mState == State.REVERSE) {
				switch(newDirection) {
				case EAST:
					mDirection = Direction.WEST;
					break;
				case WEST:
					mDirection = Direction.EAST;
					break;
				case NORTH:
					mDirection = Direction.SOUTH;
					break;
				case SOUTH:
					mDirection = Direction.NORTH;
					break;
				}
			}
		}

		
	}

	public Texture getmHeadTexture() {
		return mHeadTexture;
	}

	public void setmHeadTexture(Texture mHeadTexture) {
		this.mHeadTexture = mHeadTexture;
	}

	public Texture getmTrunkTexture() {
		return mTrunkTexture;
	}

	public void setmTrunkTexture(Texture mTrunkTexture) {
		this.mTrunkTexture = mTrunkTexture;
	}
	
	public State getmState() {
		return mState;
	}

	/** also call onStateSetListener */
	
	public void setmState(State mState) {
		this.mState = mState;
		mStateSetListener.onStateSet(mState);
//		onStateSet(mState);
	}
	

	public int getmDurationInvi() {
		return mDurationInvi;
	}

	public float getmDelay() {
		return mDelay;
	}

	public void setmDelay(float mDelay) {
		this.mDelay = mDelay;
	}

	public void setmDurationInvi(int mDurationInvi) {
		this.mDurationInvi = mDurationInvi;
		
	}

	// get called when state is re-set
	public interface OnStateSetListener {
		public void onStateSet(State state);
	}
	
	private OnStateSetListener mStateSetListener;
	
	public void setOnStateSetListener(OnStateSetListener listener) {
		mStateSetListener = listener;
	}



	// get called when head of snake is changed (snake is moving)
	// detect collision
	public interface OnHeadChangeListener {
		public void onHeadChange(Coord oldHead, Coord newHead);
	}

	private OnHeadChangeListener mHeadChangeListener;

	public void setOnHeadChangeListener(OnHeadChangeListener listener) {
		mHeadChangeListener = listener;
	}
}
