package com.uw.homework311eichmj2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class XMLProvider extends ContentProvider {

	public static final String TABLE_NAME = "articles";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_CONTENT = "content";
	public static final String COLUMN_ICON_URI = "icon_uri";
	public static final String COLUMN_DATE = "date";

	private DataBaseHelper mDataBaseHelper;
	private static final String AUTHORITY = "com.uw.homework311eichmj2.xmlprovider";
	public static final int ARTICLES = 1;

	public static final Uri ARTICLES_URI = Uri.parse("content://" + AUTHORITY);

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mDataBaseHelper = new DataBaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// TODO Auto-generated method stub

		SQLiteDatabase database = mDataBaseHelper.getReadableDatabase();
		Cursor cursor = database.query(XMLProvider.TABLE_NAME, projection,
				selection, selectionArgs, null, null, sortOrder);
		//database.close();
		return cursor;

	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase database = mDataBaseHelper.getReadableDatabase();
		database.insert(TABLE_NAME, null, values);
		//database.close();
		getContext().getContentResolver().notifyChange(uri, null);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
