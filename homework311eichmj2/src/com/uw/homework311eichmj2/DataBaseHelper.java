package com.uw.homework311eichmj2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "HomeWork311";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ XMLProvider.TABLE_NAME + " (" + XMLProvider.COLUMN_ID
			+ " integer primary key, " + XMLProvider.COLUMN_TITLE
			+ " text not null, " + XMLProvider.COLUMN_CONTENT
			+ " text not null," + XMLProvider.COLUMN_ICON_URI + " text,"
			+ XMLProvider.COLUMN_DATE + " text" + " )";

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onUpgrade(db, oldVersion, newVersion);

	}

}
