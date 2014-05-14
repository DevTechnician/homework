package com.homework2eichmj2.uw;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.text.format.Time;
import android.widget.TimePicker;

//use a time picker dialog to select alarm time and send back to activity
public class TimePickerFragment extends DialogFragment {
	Time currentTime;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		currentTime = new Time(Time.getCurrentTimezone());
		currentTime.setToNow();

		TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				if (MainActivity.dialogReceiver != null) {

					Bundle b = new Bundle();
					b.putInt("HOUR", hourOfDay);
					b.putInt("MINUTE", minute);

					MainActivity.dialogReceiver.send(0, b);
					
				}
			}
		};

		/** Opening the TimePickerDialog window */
		return new TimePickerDialog(getActivity(), listener, currentTime.hour,
				currentTime.minute, false);
	}

}
