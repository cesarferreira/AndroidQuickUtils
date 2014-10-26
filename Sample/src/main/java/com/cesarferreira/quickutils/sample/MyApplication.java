package com.cesarferreira.quickutils.sample;

import android.app.Application;

import quickutils.core.QuickUtils;

/**
 * Created by cesarferreira on 26/10/14.
 */
public class MyApplication extends Application {

    public void onCreate() {
        super.onCreate();

        // init the library
        QuickUtils.init(getApplicationContext());
    }
}
