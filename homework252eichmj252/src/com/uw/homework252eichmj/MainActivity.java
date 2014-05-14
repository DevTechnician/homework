package com.uw.homework252eichmj;

import com.squareup.otto.Subscribe;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {
	// /add and delete task from menu
	// save task list position in saved instance state
	//

	boolean isLayoutXLarge = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		isLayoutXLarge = findViewById(R.id.root_layout_xlarge) != null;

		if (isLayoutXLarge) {
			// task list is statically declared in layout
			this.getFragmentManager().beginTransaction()
					.add(R.id.frag_frame, new Description_Fragment()).commit();
		} else {

			this.getFragmentManager()
					.beginTransaction()
					.hide(this.getFragmentManager().findFragmentById(
							R.id.description_fragment)).commit();

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_task:
			AddTask_Dialog addDialog = new AddTask_Dialog();
			addDialog.show(getFragmentManager(), "add");
			return true;

		case R.id.remove_task:
			DeleteTaskDialog deleteDialog = new DeleteTaskDialog();
			deleteDialog.show(getFragmentManager(), "delete");
			
			return true;

		case android.R.id.home:
			
			this.getFragmentManager()
			.beginTransaction()
			.hide(this.getFragmentManager().findFragmentById(
					R.id.description_fragment))
			.show(this.getFragmentManager().findFragmentById(
					R.id.task_list_fragment))
					.commit();
			
			return true;
			
		default:
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Subscribe
	public void changeFragment(SwitchFragments event) {

		if (!isLayoutXLarge) {

			this.getFragmentManager()
					.beginTransaction()
					.hide(this.getFragmentManager().findFragmentById(
							R.id.task_list_fragment))
					.show(this.getFragmentManager().findFragmentById(
							R.id.description_fragment))
							.commit();

		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		BusProvider.getInstance().unregister(this);

		super.onPause();
	}

	@Override
	protected void onResume() {
		BusProvider.getInstance().register(this);

		// TODO Auto-generated method stub
		super.onResume();
	}

}
