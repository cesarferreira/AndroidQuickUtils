package com.cesarferreira.quickutils.demo;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class ParentActivity extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.list);

		ArrayList<String> categoriesArray = new ArrayList<String>();

		Resources res = getResources();
		String[] categoriesStringArray = res.getStringArray(R.array.log_array);
		categoriesArray.addAll(Arrays.asList(categoriesStringArray));

		// Create ArrayAdapter using the category list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, categoriesArray);

		// Set the ArrayAdapter as the ListView's adapter.
		mainListView.setAdapter(listAdapter);

//		mainListView.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
//				Object listItem = mainListView.getItemAtPosition(position);
//
//				char[] a = listItem.toString().toCharArray();
//
//				switch (a[0]) {
//				case 'd':
//					QuickUtils.log.i("Debug log");
//					break;
//				case 'e':
//					QuickUtils.log.i("Error log");
//					break;
//				case 'i':
//					QuickUtils.log.i("information log");
//					break;
//				case 'v':
//					QuickUtils.log.i("Verbose log");
//					break;
//				case 'w':
//					QuickUtils.log.i("Warning log");
//					break;
//				default:
//					break;
//				}
//			}
//		});
	}

}
