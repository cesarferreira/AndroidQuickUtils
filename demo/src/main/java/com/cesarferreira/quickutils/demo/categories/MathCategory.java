package com.cesarferreira.quickutils.demo.categories;

import java.util.ArrayList;
import java.util.Arrays;

import quickutils.core.QuickUtils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cesarferreira.quickutils.demo.ParentActivity;
import com.cesarferreira.quickutils.demo.R;

public class MathCategory extends ParentActivity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this.getApplicationContext();

		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.list);

		ArrayList<String> categoriesArray = new ArrayList<String>();

		Resources res = getResources();
		String[] categoriesStringArray = res.getStringArray(R.array.math_array);
		categoriesArray.addAll(Arrays.asList(categoriesStringArray));

		// Create ArrayAdapter using the category list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, categoriesArray);

		// Set the ArrayAdapter as the ListView's adapter.
		mainListView.setAdapter(listAdapter);

		mainListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
				Object listItem = mainListView.getItemAtPosition(position);

				String optionString = listItem.toString();

				int minValue = 0;
				int intValue = 15;
				float minFloatValue = (float) 0.3;
				float floatValue = (float) 2.2;
				
				String resultString = null;

				// isOdd
				if (optionString.equals("isOdd")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.isOdd(intValue);
				} else
				// isEven
				if (optionString.equals("isEven")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.isEven(intValue);
				}
				// degreesToRadians
				if (optionString.equals("degreesToRadians")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.degreesToRadians(intValue);
				}
				// radiansToDegrees
				if (optionString.equals("radiansToDegrees")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.radiansToDegrees(intValue);
				}
				// exponencial
				if (optionString.equals("exponencial")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.exponencial(intValue);
				}
				// logarithm
				if (optionString.equals("logarithm")) {
					resultString = "(" + intValue + ") = " + QuickUtils.Math.logarithm(intValue);
				}
				// logarithm
				if (optionString.equals("getRandomNumber")) {
					resultString = "(" + minValue + "," + intValue + ") = " + QuickUtils.Math.getRandomNumber(minValue, intValue);
				}
				// max
				if (optionString.equals("max")) {
					resultString = "(" + minValue + "," + intValue + ") = " + QuickUtils.Math.max(minValue, intValue);
				}
				// min
				if (optionString.equals("min")) {
					resultString = "(" + minValue + "," + intValue + ") = " + QuickUtils.Math.min(minValue, intValue);
				}	
				// abs
				if (optionString.equals("abs")) {
					resultString = "(" + floatValue + ") = " + QuickUtils.Math.abs(floatValue);
				}
				// acos
				if (optionString.equals("acos")) {
					resultString = "(" + floatValue + ") = " + QuickUtils.Math.acos(floatValue);
				}
				// asin
				if (optionString.equals("asin")) {
					resultString = "(" + floatValue + ") = " + QuickUtils.Math.asin(floatValue);
				}
				// tan
				if (optionString.equals("tan")) {
					resultString = "(" + floatValue + ") = " + QuickUtils.Math.tan(floatValue);
				}
				// atan
				if (optionString.equals("atan")) {
					resultString = "(" + floatValue + ") = " + QuickUtils.Math.atan(floatValue);
				}
				// atan2
				if (optionString.equals("atan2")) {
					resultString = "(" + minFloatValue + "," + floatValue + ") = " + QuickUtils.Math.atan2(minFloatValue, floatValue);
				}

				// Output the result
				QuickUtils.system.toast(mContext, optionString + " " + resultString);
			}
		});
	}
}
