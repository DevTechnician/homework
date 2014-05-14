package com.uw.homework312eichmj2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Artists;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class Articles_Fragment extends Fragment implements
		LoaderCallbacks<Cursor>, ListView.OnItemClickListener {

	static int LOADER_ID = 311;
	private LoaderManager.LoaderCallbacks<Cursor> mCallBacks;
	private static LoaderManager loaderManager;

	private CursorLoader articlesCursorLoader;
	private Cursor articlesCursor;

	private ArticlesListAdapter articleAdapter;

	private ListView lv;

	String[] projection = { XMLProvider.COLUMN_ID, XMLProvider.COLUMN_TITLE,
			XMLProvider.COLUMN_CONTENT, XMLProvider.COLUMN_DATE,
			XMLProvider.COLUMN_ICON_URI, XMLProvider.COLUMN_TIME_IN_MILLI_GMT };
	private String orderBy = XMLProvider.COLUMN_TIME_IN_MILLI_GMT + " ASC";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		articleAdapter = new ArticlesListAdapter(getActivity(), null);
		mCallBacks = this;
		loaderManager = getLoaderManager();
		Bundle b = new Bundle();

		loaderManager.initLoader(LOADER_ID, b, mCallBacks);

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.articles_list, null);
		lv = (ListView) v.findViewById(R.id.articleslist);
		lv.setOnItemClickListener(this);
		return v;
	}

	@Override
	public void onStart() {
		lv.setAdapter(articleAdapter);
		super.onStart();
	}

	// /////////////LOADER STUFF?????????????????

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		this.articlesCursorLoader = new CursorLoader((Context) getActivity()
				.getApplicationContext(), XMLProvider.ARTICLES_URI, projection,
				null, null, XMLProvider.COLUMN_TIME_IN_MILLI_GMT);

		return this.articlesCursorLoader;

	}

	@Override
	public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
		// TODO Auto-generated method stub

		this.articleAdapter.swapCursor(cursor);
		this.articlesCursor = cursor;
		articlesCursor.setNotificationUri(getActivity().getContentResolver(),
				XMLProvider.ARTICLES_URI);
		

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

		this.articleAdapter.swapCursor(null);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		articlesCursor.moveToPosition(position);
		
		int iconColumn = articlesCursor.getColumnIndexOrThrow(XMLProvider.COLUMN_ICON_URI);
		Content_Fragment.mImageview.setImageResource(articlesCursor.getInt(iconColumn));
		
		int titleColumn = articlesCursor.getColumnIndexOrThrow(XMLProvider.COLUMN_TITLE);
		Content_Fragment.mTitle.setText(articlesCursor.getString(titleColumn));
		
		int dateColumn = articlesCursor.getColumnIndexOrThrow(XMLProvider.COLUMN_DATE);
		Content_Fragment.mDate.setText(articlesCursor.getString(dateColumn));
		
		int contentColumn = articlesCursor
				.getColumnIndexOrThrow(XMLProvider.COLUMN_CONTENT);
		Content_Fragment.mWebView.loadData(articlesCursor
				.getString(contentColumn), "text/html", "UTF-8");
		
		MainActivity.swapFragment();

	}

	
}
