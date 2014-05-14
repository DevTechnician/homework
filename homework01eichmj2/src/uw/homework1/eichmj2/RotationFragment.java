package uw.homework1.eichmj2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Surface;

//use a fragment to display the dialog in the activity

public class RotationFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getResources().getString(R.string.dialog_orientation));
		builder.setMessage(
				getResources().getString(R.string.dialog_rotation) + " "
						+ getRotation()).setPositiveButton(
				getResources().getString(R.string.dialog_ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		return builder.create();
	}

	// get the screen orientaion in degrees
	private String getRotation() {

		String rotationdegrees = "0";
		switch (getActivity().getWindowManager().getDefaultDisplay()
				.getRotation()) {
		case Surface.ROTATION_90:
			rotationdegrees = "90";
			return rotationdegrees;
		case Surface.ROTATION_180:
			rotationdegrees = "180";
			return rotationdegrees;
		case Surface.ROTATION_270:
			rotationdegrees = "270";
			return rotationdegrees;
		default:
			break;
		}

		return rotationdegrees;
	}

}
