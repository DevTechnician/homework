package com.uw.homework253eichmj2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;

public class PlaySoundService extends Service{
	
	MediaPlayer mPlayer;
	

	@Override
	public void onCreate() {
		mPlayer = MediaPlayer.create(getApplication().getApplicationContext(), R.raw.ding);
		
		playSound();
		super.onCreate();
	}
	
	public void playSound() {
		
		int count = 5;
		while (count <= 5) {
			
			mPlayer.start();
			SystemClock.sleep(5000);
			
		}
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		mPlayer = null;
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
