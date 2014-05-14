package com.uw.homework254eichmj2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Blue_Fragment extends Fragment implements OnClickListener{

	Button blueButton;
	View blueFrame;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		blueFrame = inflater.inflate(R.layout.blue_button_frame, null);
		blueButton = (Button) blueFrame.findViewById(R.id.blue_button);
		blueButton.setOnClickListener(this);
		blueButton.bringToFront();
		
		return blueFrame;
	}

	@Override
	public void onResume() {
		
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		
		blueButton.setBackgroundColor(Color.RED);
		// TODO Auto-generated method stub
		
	}

	

}
