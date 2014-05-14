package com.uw.homework312eichmj2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Content_Fragment extends Fragment {
	
	//public static TextView mContentTextView;
	public static String mContent = "no data";
	static WebView mWebView;
	static ImageView mImageview;
	static TextView mTitle;
	static TextView mDate;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.content_webview, null);
		mWebView = (WebView)v.findViewById(R.id.web_content);
		mImageview = (ImageView) v.findViewById(R.id.imageView1);
		mTitle = (TextView) v.findViewById(R.id.textView1);
		mDate = (TextView) v.findViewById(R.id.date_textView);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		//mContentTextView = (TextView)v.findViewById(R.id.content_textview);
		return v;
	}
	

	
}
