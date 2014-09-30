package com.cesarferreira.quickutils.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import quickutils.core.QuickUtils;

public class MainActivity extends Activity {

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        QuickUtils.init("Demo-App", QuickUtils.DEBUG_MODE);


        ///////////////////////////////////////////////////////////////////////
        // PREFERENCES CATEGORY
        ///////////////////////////////////////////////////////////////////////

        // Boolean
        QuickUtils.prefs.with(mContext).save("key", true);
        QuickUtils.prefs.with(mContext).getBoolean("key", false);

        // Int
        QuickUtils.prefs.with(mContext).save("key", 15);
        QuickUtils.prefs.with(mContext).getInt("key", -1);


        ///////////////////////////////////////////////////////////////////////
        // MATH CATEGORY
        ///////////////////////////////////////////////////////////////////////

        int minValue = 0;
        int intValue = 15;
        float minFloatValue = (float) 0.3;
        float floatValue = (float) 2.2;


        // isOdd
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.isOdd(intValue));

        // isEven
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.isEven(intValue));

        // degreesToRadians
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.degreesToRadians(intValue));

        // radiansToDegrees
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.radiansToDegrees(intValue));

        // exponencial
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.exponencial(intValue));

        // logarithm
        QuickUtils.log.i("(" + intValue + ") = " + QuickUtils.math.logarithm(intValue));

        // random numbers
        QuickUtils.log.i("(" + minValue + "," + intValue + ") = " + QuickUtils.math.getRandomNumber(minValue, intValue));

        // max
        QuickUtils.log.i("(" + minValue + "," + intValue + ") = " + QuickUtils.math.max(minValue, intValue));

        // min
        QuickUtils.log.i("(" + minValue + "," + intValue + ") = " + QuickUtils.math.min(minValue, intValue));

        // abs
        QuickUtils.log.i("(" + floatValue + ") = " + QuickUtils.math.abs(floatValue));

        // acos
        QuickUtils.log.i("(" + floatValue + ") = " + QuickUtils.math.acos(floatValue));

        // asin
        QuickUtils.log.i("(" + floatValue + ") = " + QuickUtils.math.asin(floatValue));

        // tan
        QuickUtils.log.i("(" + floatValue + ") = " + QuickUtils.math.tan(floatValue));

        // atan
        QuickUtils.log.i("(" + floatValue + ") = " + QuickUtils.math.atan(floatValue));

        // atan2
        QuickUtils.log.i("(" + minFloatValue + "," + floatValue + ") = " + QuickUtils.math.atan2(minFloatValue, floatValue));


    }

    public void blurActivityClick(View view) {
        QuickUtils.system.navigateToActivity(this, TestingBlur.class);
    }
}
