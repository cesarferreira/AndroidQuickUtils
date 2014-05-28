package com.cesarferreira.quickutils.demo.categories;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cesarferreira.quickutils.demo.R;

import java.util.ArrayList;
import java.util.Arrays;

import quickutils.core.QuickUtils;

public class AnimationCategory extends Activity {

        private ListView mainListView;
        private ArrayAdapter<String> listAdapter;
        private Context mContext;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_animation_category);
            // Find the ListView resource.
            mainListView = (ListView) findViewById(R.id.list);
            mContext = this.getApplicationContext();

            ArrayList<String> categoriesArray = new ArrayList<String>();

            Resources res = getResources();
            String[] categoriesStringArray = res.getStringArray(R.array.log_array);
            categoriesArray.addAll(Arrays.asList(categoriesStringArray));

            // Create ArrayAdapter using the category list.
            listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, categoriesArray);

            // Set the ArrayAdapter as the ListView's adapter.
            mainListView.setAdapter(listAdapter);

            mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                    Object listItem = mainListView.getItemAtPosition(position);

                    String optionValue = listItem.toString();

                    // If you are developing use this (default behaviour)
                    if (optionValue.equals("Development MODE")) {

                        QuickUtils.setDebugMode(QuickUtils.DEVELOPER_MODE);
                        QuickUtils.misc.toast(mContext, "Developer mode activated");

                        return;
                    } else
                        // If you are want to put this app in PRODUCTION (disables
                        // Logcat outputs)
                        if (optionValue.equals("Production MODE")) {

                            QuickUtils.setDebugMode(QuickUtils.PRODUCTION_MODE);
                            QuickUtils.misc.toast(mContext, "Production mode activated (console output is now disabled)");

                            return;
                        }

                    char[] a = listItem.toString().toCharArray();

                    switch (a[0]) {
                        case 'd':
                            QuickUtils.log.i("Debug log");
                            break;
                        case 'e':
                            QuickUtils.log.i("Error log");
                            break;
                        case 'i':
                            QuickUtils.log.i("information log");
                            break;
                        case 'v':
                            QuickUtils.log.i("Verbose log");
                            break;
                        case 'w':
                            QuickUtils.log.i("Warning log");
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }


}
