package com.uw.homework252eichmj;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class AddTask_Dialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.add_task, null);
		final EditText taskText = (EditText) v.findViewById(R.id.editText1);
		final EditText taskDescriptionText = (EditText) v
				.findViewById(R.id.editText2);
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(v)
				// Add action buttons
				.setPositiveButton("Add",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {

								if (!taskText.getText().toString().isEmpty() && !taskDescriptionText.getText()
												.toString().isEmpty()) {
									
									TaskList_Fragment.dbu.insertNewRow(taskText
											.getText().toString(),
											taskDescriptionText.getText()
													.toString());

								}
								
								
								TaskList_Fragment.changeListData();

							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		return builder.create();

	}

}
