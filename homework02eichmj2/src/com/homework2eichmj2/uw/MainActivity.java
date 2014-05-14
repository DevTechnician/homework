package com.homework2eichmj2.uw;

import java.nio.channels.AlreadyConnectedException;
import java.sql.Date;
import java.util.Calendar;

import android.R.bool;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DigitalClock;

public class MainActivity extends FragmentActivity {

	BroadcastReceiver broadcastReceiver;
	static AlarmManager alarmManager;
	static PendingIntent pendingIntent;
	DigitalClock digitalClock;
	Boolean isTextRed = false;
	static ResultReceiver dialogReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		// digital clock
		digitalClock = (DigitalClock) findViewById(R.id.digitalClock);

		// digital clock set font
		digitalClock.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/iceland.ttf"));
		// get the Alarm manager
		alarmManager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));
		// Receiver for the time picker dialog
		dialogReceiver = new ResultReceiver(new Handler()) {

			@Override
			protected void onReceiveResult(int resultCode, Bundle resultData) {
				// save alarm time
				saveAlarm(resultData.getInt("HOUR"),
						resultData.getInt("MINUTE"));
				// set alarm time
				setAlarm(resultData.getInt("HOUR"), resultData.getInt("MINUTE"));
				// TODO Auto-generated method stub
				super.onReceiveResult(resultCode, resultData);
			}

		};

		startAlarmReciever();

	}

	// start the broadcast receiver
	private void startAlarmReciever() {
		broadcastReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub

				if (isTextRed == false) {
					digitalClock.setTextColor(Color.RED);
					isTextRed = true;
					startTimer();

				}

				else if (isTextRed == true) {
					digitalClock.setTextColor(Color.WHITE);
					isTextRed = false;
				}
			}

		};

		registerReceiver(broadcastReceiver, new IntentFilter(
				"com.homework2eichmj2.uw"));

		pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.homework2eichmj2.uw"), 0);

	}

	// 5 second interval for changing text color
	static void startTimer() {

		Time redTextTimer = new Time(Time.getCurrentTimezone());
		redTextTimer.setToNow();
		alarmManager.set(AlarmManager.RTC_WAKEUP,
				redTextTimer.toMillis(false) + 5000, pendingIntent);

	}

	// set the alarm time chosen from time picker dialog
	public void setAlarm(int hour, int minute) {

		Time alarmTime = new Time(Time.getCurrentTimezone());
		alarmTime.setToNow();
		alarmTime.set(0, minute, hour, alarmTime.monthDay, alarmTime.month,
				alarmTime.year);

		alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime.toMillis(false),
				pendingIntent);
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

		TimePickerFragment tpf = new TimePickerFragment();
		tpf.show(getSupportFragmentManager(), "tpf");
		
		return super.onOptionsItemSelected(item);

	}

	// store alarm time selected
	private void saveAlarm(int hour, int minute) {

		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit().putInt("ALARM_HOUR", hour).commit();
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit().putInt("ALARM_MINUTE", minute).commit();

	}

	// get stored alarm and set it when orientation is changed

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		int storedHour = PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext()).getInt("ALARM_HOUR", 0);
		int storedMinute = PreferenceManager.getDefaultSharedPreferences(
				getApplicationContext()).getInt("ALARM_MINUTE", 0);

		Time now = new Time(Time.getCurrentTimezone());
		now.setToNow();
		long currentMillis = now.toMillis(false);
		now.set(0, storedMinute, storedHour, now.monthDay, now.month, now.year);

		long alarmMil = now.toMillis(false);
		if (alarmMil >= currentMillis) {
			setAlarm(storedHour, storedMinute);
		}

		super.onResume();
	}

	// clean up unwanted junk when application is not running
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		alarmManager.cancel(pendingIntent);
		unregisterReceiver(broadcastReceiver);
	}

}
