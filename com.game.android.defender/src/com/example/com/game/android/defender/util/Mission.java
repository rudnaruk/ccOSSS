package com.example.com.game.android.defender.util;

public class Mission {
	private int mLevel;
	private String mTitle;
	private int mState;
	private Score mScore;
	private int mImageId;
	public Mission(int mLevel, String mTitle, int mState,
			int mImageId) {
		super();
		this.mLevel = mLevel;
		this.mTitle = mTitle;
		this.mState = mState;
		this.mImageId = mImageId;
	}
	public int getmLevel() {
		return mLevel;
	}
	public String getmTitle() {
		return mTitle;
	}
	public int getmState() {
		return mState;
	}
	public Score getmScore() {
		return mScore;
	}
	public int getmImageId() {
		return mImageId;
	}
	public void setmState(int mState) {
		this.mState = mState;
	}
	public void setmScore(Score mScore) {
		this.mScore = mScore;
	}
	
}
