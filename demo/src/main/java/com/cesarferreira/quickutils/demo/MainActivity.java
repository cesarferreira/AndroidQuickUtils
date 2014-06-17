package com.cesarferreira.quickutils.demo;

import java.util.ArrayList;
import java.util.Arrays;

import quickutils.core.QuickUtils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		QuickUtils.setTAG("SAMPLE_APP");
/*
        QuickUtils.log.d(QuickUtils.misc.getDeviceID(getApplicationContext()));

        new CustomTask<String>(String.class, new OnEventListener<String>() {
            @Override
            public void onSuccess(String object) {
                QuickUtils.log.d("SUCESSO: "+object);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }).execute();*/


		// QuickUtils.share.sendEmail(getApplicationContext(),
		// "soupas@gmail.com", "ola", "body");
//		QuickUtils.share.sendTweet(getApplicationContext(), "yeahhhhhh");
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.list);

		ArrayList<String> categoriesArray = new ArrayList<String>();

		Resources res = getResources();
		String[] categoriesStringArray = res.getStringArray(R.array.categories_array);
		categoriesArray.addAll(Arrays.asList(categoriesStringArray));

		// Create ArrayAdapter using the category list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, categoriesArray);

		// Set the ArrayAdapter as the ListView's adapter.
		mainListView.setAdapter(listAdapter);

		mainListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
				Object listItem = mainListView.getItemAtPosition(position);

				String StringClassname = "com.cesarferreira.quickutils.demo.categories." + listItem.toString() + "Category";

				try {
					QuickUtils.system.navigateToActivityByClassName(MainActivity.this, StringClassname);
				} catch (Exception e) {
					QuickUtils.system.toast(getApplicationContext(), "Not yet implemented");
				}

			}
		});

	}

}
