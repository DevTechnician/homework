package com.uw.homework253eichmj2;

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

	MediaPlayer mPlayer;
	boolean playSound = true;
	private int notificationID = 253;
	NotificationManager mNotificationManager;

	public PlaySound_IntentService() {
		super("PlaySound_IntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		mPlayer = MediaPlayer.create(getApplication().getApplicationContext(),
				R.raw.ding);
		sendNotification("Now playing BEEP!!!!!");
		playSound();
		// TODO Auto-generated method stub

	}

	public void playSound() {

		int count = 5;
		while (playSound) {

			mPlayer.start();
			SystemClock.sleep(5000);

		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		playSound = false;
		mPlayer = null;
		mNotificationManager.cancel(notificationID);
		super.onDestroy();
	}

	private void sendNotification(String message) {

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("BEEP! BEEP! BEEP!").setContentText(message);

		// Creates an explicit intent for an Activity in your application
		// <---------------------change
		// activity------------------------------------------------>
		Intent resultIntent = new Intent(this, MainActivity.class);
		/* Intent resultIntent = new Intent(this, MainActivity.class); */

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		// <---------------------change
		// activity------------------------------------------------>
		// stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addParentStack(MainActivity.class);

		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		mBuilder.setAutoCancel(true);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(notificationID, mBuilder.build());

	}

}
