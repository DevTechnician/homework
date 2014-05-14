package com.uw.homework311eichmj2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataRetreiver {

	XmlPullParserFactory mParserFactory;
	XmlPullParser mParser;
	DataBaseHelper mDatabaseHelper;
	Context mContext;

	public void loadXMLData(Context context) {
		// TODO Auto-generated method stub
		this.mContext = context;
		mDatabaseHelper = new DataBaseHelper(context);
		SQLiteDatabase database = mDatabaseHelper.getReadableDatabase();
		database.delete(XMLProvider.TABLE_NAME, null, null);
		//database.close();
		try {
			mParserFactory = XmlPullParserFactory.newInstance();
			mParserFactory.setNamespaceAware(true);
			mParser = mParserFactory.newPullParser();

			InputStream mDataStream = context.getAssets().open("data.xml");
			mParser.setInput(mDataStream, null);

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		parseData(mParser);

	}

	private void parseData(XmlPullParser parser) {

		ParsedItem currentItem = null;

		int mParserEventType = -1;

		try {
			mParserEventType = parser.getEventType();
		} catch (XmlPullParserException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (mParserEventType != XmlPullParser.END_DOCUMENT) {

			String mTag = null;

			switch (mParserEventType) {

			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				mTag = parser.getName();
				if (mTag.equalsIgnoreCase("item")) {
					currentItem = new ParsedItem();

				} else if (currentItem != null) {
					if (mTag.equalsIgnoreCase("title")) {

						try {
							currentItem.mTitle = parser.nextText();
						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (mTag.equalsIgnoreCase("content")) {

						try {
							currentItem.mContent = parser.nextText();
						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
				break;

			case XmlPullParser.END_TAG:
				mTag = parser.getName();
				if (mTag.equalsIgnoreCase("item") && currentItem != null) {
					/*
					 * mDataList.add(currentItem);
					 * ADB.insertArticle(currentItem.mTitle,
					 * currentItem.mContent, "", "");
					 */
					ContentValues mContentVals = new ContentValues();
					mContentVals.put(XMLProvider.COLUMN_TITLE,
							currentItem.mTitle);
					mContentVals.put(XMLProvider.COLUMN_CONTENT,
							currentItem.mContent);
					mContentVals.put(XMLProvider.COLUMN_DATE,
							String.valueOf(Calendar.getInstance().DATE));
					mContentVals.put(XMLProvider.COLUMN_ICON_URI, "");
					mContext.getContentResolver().insert(
							XMLProvider.ARTICLES_URI, mContentVals);

				}

			}
			try {
				mParserEventType = parser.next();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class ParsedItem {

		String mTitle;
		String mContent;

	}

}
