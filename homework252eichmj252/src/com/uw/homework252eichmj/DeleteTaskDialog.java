package com.uw.homework252eichmj;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class DeleteTaskDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//DataBaseUtility dataBase = new DataBaseUtility(getActivity());

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final Cursor c = TaskList_Fragment.mCursor;
		String s = c.getString(1);
		
		
		builder.setTitle("Delete Task")
		.setCursor(c, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				c.moveToPosition(id);
				TaskList_Fragment.dbu.deleteTask(c.getLong(0));
				TaskList_Fragment.changeListData();
			}
		}, "task")
		
		.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});
		
		// Create the AlertDialog object and return it
		return builder.create();

	}
}
