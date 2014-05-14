package com.uw.homework254eichmj2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

//rotate causes x,y coords to go out of bounds.. catching the layout id (layout-land) possible to swap x,y coords
//setting/catching coords x,y before views are visible causes havoc!!!
//

public class MainActivity extends FragmentActivity implements OnTouchListener {

	Button redButton;
	Button blueButton;
	Button resetButton;

	private boolean isLandscape;

	private float mLastTouchX;
	private float mLastTouchY;
	private float mViewPosX;
	private float mViewPosY;

	private static final int INVALID_POINTER_ID = -1;
	private int mActivePointerId = INVALID_POINTER_ID;

	private SharedPreferences mSharedPrefs;

	// set some strings to reduce typing and errors

	private final String LAYOUT_WAS_LAND = "LAYOUT_WAS_LAND";

	private final String RESET_REDX_POSITION = "RESET_REDX";
	private final String RESET_REDY_POSITION = "RESET_REDY";
	private final String RESET_BLUEX_POSITION = "RESET_BLUEX";
	private final String RESET_BLUEY_POSITION = "RESET_BLUEY";

	private final String SAVED_ORIGINAL_POSITIONS = "SAVED_ORIGINAL_POSITIONS";
	private final String SAVED_LAST_POSITIONS = "SAVED_LAST_POSITIONS";

	private final String LAST_REDX_POSITION = "SET_REDX";
	private final String LAST_REDY_POSITION = "SET_REDY";
	private final String LAST_BLUEX_POSITION = "SET_BLUEX";
	private final String LAST_BLUEY_POSITION = "SET_BLUEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_test);
		isLandscape = findViewById(R.id.root_layout_landscape) != null;
		mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		redButton = (Button) findViewById(R.id.button1);
		blueButton = (Button) findViewById(R.id.button2);
		resetButton = (Button) findViewById(R.id.button3);

		redButton.setOnTouchListener(this);
		blueButton.setOnTouchListener(this);
		resetButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				redButton.setX(mSharedPrefs.getFloat(RESET_REDX_POSITION, -1));
				redButton.setY(mSharedPrefs.getFloat(RESET_REDY_POSITION, -1));
				blueButton.setX(mSharedPrefs.getFloat(RESET_BLUEX_POSITION, -1));
				blueButton.setY(mSharedPrefs.getFloat(RESET_BLUEY_POSITION, -1));
				mSharedPrefs.edit().putBoolean(SAVED_LAST_POSITIONS, false)
						.commit();
				// TODO Auto-generated method stub

			}

		});

		setButtonPositions();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setButtonPositions() {

		if (mSharedPrefs.getBoolean(SAVED_LAST_POSITIONS, true) == true) {

			// setting the position b4 the views are visible causes the saved
			// x,y to be added to the start points the layout gives them
			// example button y position is saved at 800 its layout says 1000..
			// y
			// position is set to 1800..
			// possible use a layout/view on focus or on visible listener then
			// set coords x,y..might be choppy and its friday night we will see
			// maybe

			redButton.setX(mSharedPrefs.getFloat(LAST_REDX_POSITION, -1)
					- mSharedPrefs.getFloat(RESET_REDX_POSITION, 0));
			redButton.setY(mSharedPrefs.getFloat(LAST_REDY_POSITION, -1)
					- mSharedPrefs.getFloat(RESET_REDY_POSITION, 0));
			blueButton.setX(mSharedPrefs.getFloat(LAST_BLUEX_POSITION, -1)
					- mSharedPrefs.getFloat(RESET_BLUEX_POSITION, 0));
			blueButton.setY(mSharedPrefs.getFloat(LAST_BLUEY_POSITION, -1)
					- mSharedPrefs.getFloat(RESET_BLUEY_POSITION, 0));

		}

	}

	private void saveOriginalViewPositions() {

		if (mSharedPrefs.getBoolean(SAVED_ORIGINAL_POSITIONS, false) == false) {
			mSharedPrefs.edit().putFloat(RESET_REDX_POSITION, redButton.getX())
					.commit();
			mSharedPrefs.edit().putFloat(RESET_REDY_POSITION, redButton.getY())
					.commit();
			mSharedPrefs.edit()
					.putFloat(RESET_BLUEX_POSITION, blueButton.getX()).commit();
			mSharedPrefs.edit()
					.putFloat(RESET_BLUEY_POSITION, blueButton.getY()).commit();
			mSharedPrefs.edit().putBoolean(SAVED_ORIGINAL_POSITIONS, true)
					.commit();
		}

	}

	private void saveCurrentViewPositions() {

		mSharedPrefs.edit().putFloat(LAST_REDX_POSITION, redButton.getX())
				.commit();
		mSharedPrefs.edit().putFloat(LAST_REDY_POSITION, redButton.getY())
				.commit();
		mSharedPrefs.edit().putFloat(LAST_BLUEX_POSITION, blueButton.getX())
				.commit();
		mSharedPrefs.edit().putFloat(LAST_BLUEY_POSITION, blueButton.getY())
				.commit();
		mSharedPrefs.edit().putBoolean(SAVED_LAST_POSITIONS, true).commit();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		saveOriginalViewPositions();

		final int action = MotionEventCompat.getActionMasked(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {

			Intent playSound = new Intent(getApplication(),
					PlaySound_IntentService.class);
			playSound.putExtra(PlaySound_IntentService.SOUND_ID, R.raw.ding);
			getApplication().getApplicationContext().startService(playSound);

			if (v.getId() == redButton.getId()) {
				redButton.setBackgroundResource(R.drawable.red_button_on_touch);
			}

			if (v.getId() == blueButton.getId()) {
				blueButton
						.setBackgroundResource(R.drawable.blue_button_on_touch);
			}

			final int pointerIndex = MotionEventCompat.getActionIndex(event);
			final float startX = MotionEventCompat.getX(event, pointerIndex);
			final float startY = MotionEventCompat.getY(event, pointerIndex);

			mLastTouchX = startX;
			mLastTouchY = startY;

			mActivePointerId = MotionEventCompat.getPointerId(event,
					pointerIndex);

			mViewPosX = v.getX();
			mViewPosY = v.getY();

			break;
		}
		case MotionEvent.ACTION_MOVE: {

			final int pointerIndex = MotionEventCompat.findPointerIndex(event,
					mActivePointerId);

			final float x = MotionEventCompat.getX(event, pointerIndex);
			final float y = MotionEventCompat.getY(event, pointerIndex);

			// Calculate the distance moved
			final float dx = x - mLastTouchX;
			final float dy = y - mLastTouchY;

			v.setX(mViewPosX += dx);
			v.setY(mViewPosY += dy);

			break;
		}
		case MotionEvent.ACTION_UP: {

			if (v.getId() == redButton.getId()) {
				redButton.setBackgroundResource(R.drawable.red_button);
			}

			if (v.getId() == blueButton.getId()) {
				blueButton.setBackgroundResource(R.drawable.blue_button);
			}

			Intent playSound = new Intent(getApplication(),
					PlaySound_IntentService.class);
			playSound.putExtra(PlaySound_IntentService.SOUND_ID, R.raw.tada);
			getApplication().getApplicationContext().startService(playSound);

			mActivePointerId = INVALID_POINTER_ID;
			saveCurrentViewPositions();
		}
		default:
			break;
		}
		// TODO Auto-generated method stub
		return false;
	}
}
