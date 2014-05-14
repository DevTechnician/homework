package com.uw.homework254eichmj2;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

public class PlaySound_IntentService extends IntentService {

	static final String SOUND_ID = "SOUND_ID";
	MediaPlayer mPlayer;

	public PlaySound_IntentService() {
		super("PlaySound_IntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		int song_ID = intent.getIntExtra(SOUND_ID, -1);

		mPlayer = MediaPlayer.create(getApplication().getApplicationContext(),
				song_ID);

		playSound();
		// TODO Auto-generated method stub

	}

	public void playSound() {

		mPlayer.start();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		mPlayer = null;

		super.onDestroy();
	}

}
