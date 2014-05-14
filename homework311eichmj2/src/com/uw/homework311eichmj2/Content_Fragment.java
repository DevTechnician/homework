package com.uw.homework311eichmj2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Content_Fragment extends Fragment {
	
	public static TextView mContentTextView;
	public static String mContent = "no data";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.content, null);
		mContentTextView = (TextView)v.findViewById(R.id.content_textview);
		return v;
	}
	

	
}
