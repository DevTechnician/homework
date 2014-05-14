package com.uw.homework253eichmj2;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button startStopButton;
	boolean soundIsPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		startStopButton = (Button)findViewById(R.id.button1);
		startStopButton.setOnClickListener(this);
		soundIsPlaying = isMyServiceRunning();
		
		if (soundIsPlaying) {
			
			startStopButton.setText("Stop");
			
		}else {
			startStopButton.setText("Start");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent playSound = new Intent(getApplication(), PlaySound_IntentService.class);
		if (soundIsPlaying) {
			
			getApplication().getApplicationContext().stopService(playSound);
			soundIsPlaying = false;
			startStopButton.setText("Start");
			//could call isMyServiceRunning but its another thread and it might not be when this method runs
		}else {
			getApplication().getApplicationContext().startService(playSound);
			soundIsPlaying = true;
			startStopButton.setText("Stop");
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
	
	private boolean isMyServiceRunning() {
	    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("com.uw.homework253eichmj2.PlaySound_IntentService".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}

}
