package com.uw.homework251eichmj2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VerificationActivity extends Activity{
	TextView email;
	TextView password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.verification);
		getActionBar().hide();
		
		Intent passedIntent = getIntent();
		email = (TextView)findViewById(R.id.emailTextView);
		password = (TextView)findViewById(R.id.passwordTextView);
		
		String emailFromIntent = passedIntent.getStringExtra("email");
		String passwordFromIntent = passedIntent.getStringExtra("password");
		
		email.setText(emailFromIntent);
		password.setText(passwordFromIntent);
		
		
	}
	
	

}
