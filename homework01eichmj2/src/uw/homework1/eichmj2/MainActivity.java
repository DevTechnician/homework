package uw.homework1.eichmj2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {
	/*
	 * change menu item text in the languages strings change back button on
	 * green activity set language strings for dialogue
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);// set the content view
		Button startButton = (Button) findViewById(R.id.start_button);// get the button
		startButton.setOnClickListener(this);// set the buttons on click listener as this activity

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int r = this.getWindowManager().getDefaultDisplay().getRotation();
		DialogFragment dialog = new RotationFragment();
		dialog.show(getSupportFragmentManager(), "rotation");

		return super.onOptionsItemSelected(item);

	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent(this, GreenActivity.class);
		startActivity(intent);
		

	}

}
