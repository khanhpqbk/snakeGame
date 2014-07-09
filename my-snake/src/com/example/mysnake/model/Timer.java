package com.example.mysnake.model;
/**
 * timer sida, ko dung` multiThread
 * */
public class Timer {
	private float mTime;
	
//	public static final floa
	public static final float MAX_INVI = 5;
	public static final float MAX_REVERSE = 5;
	public static final float MAX_ITEM_INVI = 20;
	public static final float MAX_ITEM_REV = 20;
	
	public Timer() {
		
	}

	public float getmTime() {
		return mTime;
	}

	public void setmTime(float mTime) {
		this.mTime = mTime;
	}
	
	/**
	 *  mTime += delta
	 * */
	public void update(float delta) {
		mTime += delta;
	}
	
	/**
	 * mTime = 0;
	 * */
	public void reset() {
		mTime = 0;
	}
	
}
