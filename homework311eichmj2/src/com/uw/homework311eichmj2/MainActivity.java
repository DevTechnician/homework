package com.uw.homework311eichmj2;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class MainActivity extends FragmentActivity {

	static FragmentManager fm;

	private DataRetreiver xmlDataetreiver;

	private SensorManager mSensorManager;

	private float mAccelLast;

	private float mAccelCurrent;

	private float mAccel;

	private long shakeTimer;

	private SensorEventListener mShakeListener;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		
		setContentView(R.layout.main_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setProgressBarIndeterminateVisibility(true);
		xmlDataetreiver = new DataRetreiver();
		xmlDataetreiver.loadXMLData(getApplicationContext());

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;

		mShakeListener = new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub

				float x = event.values[0];
				float y = event.values[1];
				float z = event.values[2];
				mAccelLast = mAccelCurrent;
				mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z
						* z));
				float delta = mAccelCurrent - mAccelLast;
				mAccel = mAccel * 0.1f + delta;
				if (mAccel > 4) {

					if (shakeTimer + 500 < System.currentTimeMillis()) {
						setProgressBarIndeterminateVisibility(true);
						shakeTimer = System.currentTimeMillis();
						xmlDataetreiver.loadXMLData(getApplicationContext());
					

					}
				}

			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub

			}
		};

		fm = getSupportFragmentManager();

		fm.beginTransaction().add(new Articles_Fragment(), "list").commit();

		fm.beginTransaction().hide(fm.findFragmentById(R.id.content_fragment))
				.commit();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mSensorManager.unregisterListener(mShakeListener);
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mSensorManager.registerListener(mShakeListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case android.R.id.home:

			fm.beginTransaction()
					.show(fm.findFragmentById(R.id.articles_list_fragment))
					.commit();
			fm.beginTransaction()
					.hide(fm.findFragmentById(R.id.content_fragment)).commit();

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	static void swapFragment() {
		fm.beginTransaction()
				.hide(fm.findFragmentById(R.id.articles_list_fragment))
				.commit();
		fm.beginTransaction().show(fm.findFragmentById(R.id.content_fragment))
				.commit();
	}
	
	
		
	
	

}
