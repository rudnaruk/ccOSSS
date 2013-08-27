package com.example.com.game.android.defender.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public abstract class BaseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initActivity();
		initView();
		initController(this);
	}

	@Override
	protected void onDestroy() {
		cleanUp();
		super.onDestroy();
	}

	public abstract void initActivity();
	public abstract void initView();
	public abstract void initController(Context context);
	public abstract void cleanUp();
}
