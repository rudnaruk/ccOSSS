package com.example.com.game.android.defender;

import com.example.com.game.android.defender.controller.GameController;
import com.example.com.game.android.defender.controller.MainController;
import com.example.com.game.android.defender.core.BaseActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

public class GameActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void initActivity() {
		
	}

	@Override
	public void initView() {
		
	}

	@Override
	public void initController(Context context) {
		GameController mGameController = new GameController(this);
	}

	@Override
	public void cleanUp() {
		
	}
}
