package com.example.americansignlingua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	Handler h;
	Runnable r;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		h = new Handler();
		r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(Splash.this, ShowSign.class);
				i.putExtra("key_value", 0);
				startActivity(i);
				finish();
			}
		};
		h.postDelayed(r, SPLASH_TIME_OUT);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		h.removeCallbacks(r);
	}
}
