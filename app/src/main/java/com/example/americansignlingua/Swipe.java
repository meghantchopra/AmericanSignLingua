package com.example.americansignlingua;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Swipe extends Activity implements OnTouchListener {

	LinearLayout ivSwipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.swipeview);

		ivSwipe = (LinearLayout) findViewById(R.id.ivSwipe);
		
		ivSwipe.setOnTouchListener(this);
		

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	//	super.onBackPressed();
	
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		finish();
		return false;
	}

}
