package com.example.com.game.android.defender.util;

public abstract class Tower {
	private int mType;
	private int mPrice;
	private int mLevel;
	private int mPower;
	private int mSpeed;
	private Position mPosition;
	abstract public void onCreated();
	abstract public void onAttrack();
	abstract public void onDestroyed();
	abstract public void onDraw();
	public Tower() {
		onCreated();
	}
	
}
