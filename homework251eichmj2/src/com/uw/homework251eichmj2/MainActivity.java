package com.uw.homework251eichmj2;

import java.io.ObjectInputStream.GetField;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.NinePatchDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText emailInput;
	EditText passwordInput;
	ImageButton signIn;

	// *** need on focus background image change for both inputs
	// *** need on text change to star out password input
	// need verification of password exists and if email exists and is email...
	// notify user if it does not
	// make sign in button

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		getActionBar().hide();
		emailInput = (EditText) findViewById(R.id.editTextEmail);
		passwordInput = (EditText) findViewById(R.id.editTextPassword);
		signIn = (ImageButton) findViewById(R.id.imageButton1);

		passwordInput.setTransformationMethod(HideReturnsTransformationMethod
				.getInstance());

		passwordInput.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

				passwordInput
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean emailVerified = isValidEmail(emailInput.getText());
				boolean passwordVerified = isPassword();
				if (!emailVerified) {
					Toast.makeText(getApplication(),
							"You must enter a valid Email", Toast.LENGTH_LONG)
							.show();
				}

				if (passwordVerified && emailVerified) {
					Intent i = new Intent(getApplicationContext(),VerificationActivity.class);
					i.putExtra("email", emailInput.getText().toString());
					i.putExtra("password", passwordInput.getText().toString());
					startActivity(i);

				}
			}
		});

	}

	private boolean isPassword() {
		String sUsername = passwordInput.getText().toString();
		if (sUsername.matches("")) {
			Toast.makeText(this, "You did not enter a password",
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}

	private boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
