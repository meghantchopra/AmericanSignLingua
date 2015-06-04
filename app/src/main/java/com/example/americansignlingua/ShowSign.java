package com.example.americansignlingua;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ShowSign extends Activity implements OnClickListener,
		OnTouchListener {

	public Integer[] image_all = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k,
			R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
			R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s,
			R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w,
			R.drawable.x, R.drawable.y, R.drawable.z };

	ImageView prv, next, play, pause;
	ImageView ivSign;
	int position = 0;

	public boolean status = false;
	public CountDownTimer cdt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showsign);

		Intent intent = new Intent(this, Swipe.class);
		startActivity(intent);

		ivSign = (ImageView) findViewById(R.id.ivSign);
		ivSign.setBackgroundResource(R.drawable.a);

		prv = (ImageView) findViewById(R.id.btn_prv);
		next = (ImageView) findViewById(R.id.btn_next);
		play = (ImageView) findViewById(R.id.btn_play);

		prv.setOnClickListener(this);
		next.setOnClickListener(this);
		play.setOnClickListener(this);

		ivSign.setOnTouchListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_prv:
			position--;
			if (position < 0) {
				position = image_all.length - 1;
			}
			ivSign.setBackgroundResource(image_all[position]);
			if (status == true) {
				play.setBackgroundResource(R.drawable.play);
				cdt.cancel();
				status = false;
			}
			break;

		case R.id.btn_next:
			position++;
			if (position > image_all.length - 1) {
				position = 0;
			}
			ivSign.setBackgroundResource(image_all[position]);
			if (status == true) {
				play.setBackgroundResource(R.drawable.play);
				cdt.cancel();
				status = false;
			}

			break;
		case R.id.btn_play:

			if (status == false) {
				play.setBackgroundResource(R.drawable.pause);
				fun_slideshow();
				status = true;
			} else {
				play.setBackgroundResource(R.drawable.play);
				cdt.cancel();
				status = false;
			}

			break;
		default:
			break;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (status == true) {
			play.setBackgroundResource(R.drawable.play);
			cdt.cancel();
			status = false;
		}

	}

	private void fun_slideshow() {

		cdt = new CountDownTimer(2000, 1) {

			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				position++;
				if (position > image_all.length - 1) {
					position = 0;
				}
				ivSign.setBackgroundResource(image_all[position]);
				fun_slideshow();
			}
		}.start();
	}

	float initialX, finalX;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub0
		Log.i("TAG", "Touch");
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			initialX = event.getX();
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			finalX = event.getX();
			if (Math.abs(initialX - finalX) < 100) {

			} else if (finalX - initialX >= 100) {
				// left
				position--;
				if (position < 0) {
					position = image_all.length - 1;
				}
				ivSign.setBackgroundResource(image_all[position]);

			} else {
				// right
				position++;
				if (position > image_all.length - 1) {
					position = 0;
				}
				ivSign.setBackgroundResource(image_all[position]);

			}

		}
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle("Exit?");
		builder.setMessage("Are yu sure you want to exit?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

}
