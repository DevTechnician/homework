package uw.homework1.eichmj2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);// reuse same xml layout

		Button backButton = (Button) findViewById(R.id.start_button);// get the button
																	
		backButton.setVisibility(backButton.INVISIBLE);// hide the button

		View root = backButton.getRootView();// get the buttons root view to
												// change the background color
		root.setBackgroundColor(Color.GREEN);// change the background color

		/*
		 * this sets a different views background
		 * getWindow().getDecorView().setBackgroundColor(Color.GREEN);
		 */
	}

}
