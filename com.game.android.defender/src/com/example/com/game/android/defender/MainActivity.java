package com.example.com.game.android.defender;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.example.com.game.android.defender.controller.MainController;
import com.example.com.game.android.defender.core.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		MainController mMainController = new MainController(this);
	}

	@Override
	public void cleanUp() {
		
	}

}
