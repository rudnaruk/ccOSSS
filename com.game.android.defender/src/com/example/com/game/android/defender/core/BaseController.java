package com.example.com.game.android.defender.core;

import android.app.Activity;


public abstract class BaseController {
	private BaseActivity mContext;
	public BaseController(BaseActivity mContext) {
		this.mContext = mContext;
		initController();
	}
	public Activity getActivity(){
		return mContext;
	}
	public abstract void initController();
}
