package com.uw.homework312eichmj2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticlesListAdapter extends CursorAdapter {

	private LayoutInflater mLayoutInflater;

	public ArticlesListAdapter(Context context, Cursor c) {

		super(context, c);

		mLayoutInflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		return mLayoutInflater.inflate(R.layout.row, parent, false);

	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		ViewHolder holder = (ViewHolder) view.getTag();

		if (holder == null) {

			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.textView1);
			
			holder.date = (TextView) view.findViewById(R.id.date_textView);
			
			holder.icon = (ImageView) view.findViewById(R.id.imageView1);

			holder.titleColumn = cursor
					.getColumnIndexOrThrow(XMLProvider.COLUMN_TITLE);
			
			holder.dateColumn = cursor
					.getColumnIndexOrThrow(XMLProvider.COLUMN_DATE);
			
			holder.iconColumn = cursor.getColumnIndexOrThrow(XMLProvider.COLUMN_ICON_URI);

			holder.title.setText(cursor.getString(holder.titleColumn));
			holder.date.setText(cursor.getString(holder.dateColumn));
			holder.icon.setImageResource(cursor.getInt(holder.iconColumn));

		}
	}

	private class ViewHolder {
		ImageView icon;
		TextView title;
		TextView date;

		int titleColumn;
		int iconColumn;
		int dateColumn;

	}
}
