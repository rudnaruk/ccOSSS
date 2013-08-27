package com.example.com.game.android.defender.util;

public abstract class Monster {
	private int mType;
	private int mLive;
	private int mLevel;
	private int mSpeed;
	private Position mPosition;
	abstract public void onMove();
	abstract public void onCreated();
	abstract public void onAttracked();
	abstract public void onDestroyed();
	abstract public void onDraw();
	public Monster() {
		onCreated();
	}
}
