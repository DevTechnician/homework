package com.uw.homework312eichmj2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.text.format.DateFormat;

public class RSSReaderTask extends AsyncTask<String, Void, Void> {

	URL mUrl;
	XmlPullParserFactory mFactory;
	XmlPullParser mParser;
	int mIcon;
	String mTag;

	RSSReaderTask(int icon, String tag) {
		this.mIcon = icon;
		this.mTag = tag;

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		BusProvider.getInstance().post(new ProgressEvent(true));

		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub

		super.onProgressUpdate(values);
	}

	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			mUrl = new URL(params[0]);
			mFactory = XmlPullParserFactory.newInstance();
			mParser = mFactory.newPullParser();

			mParser.setInput(mUrl.openConnection().getInputStream(), "UTF_8");

			parseData(mParser);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		BusProvider.getInstance().post(new ProgressEvent(false));

		super.onPostExecute(result);
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
					} else if (mTag.equalsIgnoreCase("description")) {

						try {
							currentItem.mContent = parser.nextText();
						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (mTag.equalsIgnoreCase("pubDate")) {

						try {
							currentItem.mDate = parser.nextText();
						} catch (XmlPullParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} // else if (mTag.equalsIgnoreCase("")){}

				}
				break;

			case XmlPullParser.END_TAG:
				mTag = parser.getName();
				if (mTag.equalsIgnoreCase("item") && currentItem != null) {

					ContentValues mContentVals = new ContentValues();

					mContentVals.put(XMLProvider.COLUMN_TITLE,
							currentItem.mTitle);

					mContentVals.put(XMLProvider.COLUMN_CONTENT,
							currentItem.mContent);

					mContentVals
							.put(XMLProvider.COLUMN_DATE, currentItem.mDate);

					mContentVals.put(XMLProvider.COLUMN_ICON_URI, mIcon);
					
					mContentVals.put(XMLProvider.COLUMN_TIME_IN_MILLI_GMT, parseDate(currentItem.mDate));

					MainActivity.MCONTEXT.getContentResolver().insert(
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

	private long parseDate(String pubDate) {
		long timeInMilliseconds = 0;
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss zzz");
		try {
			Date date = formatter.parse(pubDate);
			timeInMilliseconds = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeInMilliseconds;
		
	}

	private class ParsedItem {

		String mTitle;
		String mContent;
		String mDate;
		int mIconURL;

	}
}
