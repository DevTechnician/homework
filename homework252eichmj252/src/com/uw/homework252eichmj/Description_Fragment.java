package com.uw.homework252eichmj;

import com.squareup.otto.Subscribe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Description_Fragment extends Fragment {

	TextView descriptionText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.description_text, null);
		descriptionText = (TextView)v.findViewById(R.id.description_textview);
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		descriptionText.setText("Task Description");
		super.onActivityCreated(savedInstanceState);
	}
	/// Otto Bus Provider for fragment communication
	@Subscribe
	public void OnTaskSelected(OnTaskSelect event){
		descriptionText.setText(event.descriptionText);
		BusProvider.getInstance().post(new SwitchFragments(true));
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		BusProvider.getInstance().unregister(this);
		super.onPause();
	}

	@Override
	public void onResume() {
		BusProvider.getInstance().register(this);
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	

}
