package com.cesarferreira.quickutils.sample.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesarferreira.quickutils.sample.R;
import com.cesarferreira.quickutils.sample.activities.BlurActivity;
import com.cesarferreira.quickutils.sample.models.WeatherEntity;
import com.cesarferreira.quickutils.sample.views.Utils;

import quickutils.core.QuickUtils;
import quickutils.core.image.cache.ImageLoaderHandler;
import quickutils.core.interfaces.RequestCallback;
import quickutils.core.models.LocationModel;
import quickutils.core.rest.RequestError;

public class MainActivity extends Activity {

    private TextView textView;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject dependency
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        ///////////////////////////////////////////////////////////////////////
        // IMAGE CATEGORY
        ///////////////////////////////////////////////////////////////////////

        QuickUtils.imageCache.load(Utils.IMAGE_URL, imageView);
        QuickUtils.imageCache.load(Utils.IMAGE_URL, imageView, R.drawable.dummy, R.drawable.error);

        ///////////////////////////////////////////////////////////////////////
        // SYSTEM CATEGORY
        ///////////////////////////////////////////////////////////////////////

        QuickUtils.system.toast("this is a toast");

        QuickUtils.system.vibrate(50);


        ///////////////////////////////////////////////////////////////////////
        // TIMER CATEGORY
        ///////////////////////////////////////////////////////////////////////

        String timerTag = "test";

        // start the timer
        QuickUtils.timer.start(timerTag);

        for (int i = 0; i < 100; i++) {
            // Sleep just for
            QuickUtils.system.sleep(1);
            if (i % 10 == 0) {
                QuickUtils.log.i("more 10% took: " + QuickUtils.timer.tick(timerTag) + " ms");
            }
        }

        // stop the timer
        long difference = QuickUtils.timer.stop(timerTag);

        QuickUtils.log.i("time past: " + difference + " ms");


        ///////////////////////////////////////////////////////////////////////
        // PREFERENCES CATEGORY
        ///////////////////////////////////////////////////////////////////////

        // Boolean
        QuickUtils.prefs.save("key", true);
        boolean someBoolean = QuickUtils.prefs.getBoolean("key", false);
        QuickUtils.log.i(String.format("bool from preferences - %b", someBoolean));

        // Int
        QuickUtils.prefs.save("key", 15);
        int someInt = QuickUtils.prefs.getInt("key", -1);
        QuickUtils.log.i(String.format("int from preferences - %d", someInt));

        ///////////////////////////////////////////////////////////////////////
        // MATH CATEGORY
        ///////////////////////////////////////////////////////////////////////

        int minValue = 0;
        int intValue = 15;
        float minFloatValue = (float) 0.3;
        float floatValue = (float) 2.2;
        float anotherFLoatValue = (float) 0.2;

        // isOdd
        QuickUtils.log.i("isOdd - (" + intValue + ") = " + QuickUtils.math.isOdd(intValue));

        // isEven
        QuickUtils.log.i("isEven - (" + intValue + ") = " + QuickUtils.math.isEven(intValue));

        // degreesToRadians
        QuickUtils.log.i("degreesToRadians - (" + intValue + ") = " + QuickUtils.math.degreesToRadians(intValue));

        // radiansToDegrees
        QuickUtils.log.i("radiansToDegrees - (" + intValue + ") = " + QuickUtils.math.radiansToDegrees(intValue));

        // exponencial
        QuickUtils.log.i("exponencial - (" + intValue + ") = " + QuickUtils.math.exponencial(intValue));

        // logarithm
        QuickUtils.log.i("logarithm - (" + intValue + ") = " + QuickUtils.math.logarithm(intValue));

        // random numbers
        QuickUtils.log.i("random - (" + minValue + "," + intValue + ") = " + QuickUtils.math.getRandomNumber(minValue, intValue));

        // max
        QuickUtils.log.i("max - (" + minValue + "," + intValue + ") = " + QuickUtils.math.max(minValue, intValue));

        // min
        QuickUtils.log.i("min - (" + minValue + "," + intValue + ") = " + QuickUtils.math.min(minValue, intValue));

        // abs
        QuickUtils.log.i("abs - (" + floatValue + ") = " + QuickUtils.math.abs(floatValue));

        // acos
        QuickUtils.log.i("acos - (" + anotherFLoatValue + ") = " + QuickUtils.math.acos(anotherFLoatValue));

        // asin
        QuickUtils.log.i("asin - (" + anotherFLoatValue + ") = " + QuickUtils.math.asin(anotherFLoatValue));

        // tan
        QuickUtils.log.i("tan - (" + floatValue + ") = " + QuickUtils.math.tan(floatValue));

        // atan
        QuickUtils.log.i("atan - (" + floatValue + ") = " + QuickUtils.math.atan(floatValue));

        // atan2
        QuickUtils.log.i("atan2 - (" + minFloatValue + "," + floatValue + ") = " + QuickUtils.math.atan2(minFloatValue, floatValue));


        ///////////////////////////////////////////////////////////////////////
        // TEXT CATEGORY
        ///////////////////////////////////////////////////////////////////////

        QuickUtils.log.i("Unaccent - " + QuickUtils.text.unAccent("ééééé çedilha"));

        ///////////////////////////////////////////////////////////////////////
        // LOCATION CATEGORY
        ///////////////////////////////////////////////////////////////////////
        LocationModel locationModel = QuickUtils.location.getLocationByCoordinates(38.7471236,-9.1532266);
        QuickUtils.log.i("Location: ");
        QuickUtils.log.i("latitude --> " + locationModel.latitude);
        QuickUtils.log.i("longitude --> " + locationModel.longitude);
        QuickUtils.log.i("address --> " + locationModel.address);
        QuickUtils.log.i("city --> " + locationModel.city);
        QuickUtils.log.i("country --> " + locationModel.country);
    }

    public void blurActivityClick(View view) {
        QuickUtils.system.navigateToActivity(this, BlurActivity.class);
    }

    public void restRequest(View view) {

        String REQUEST_TAG = "tag";

        QuickUtils.rest.connect()
                .createRequest()
                .get()
                .pathUrl("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139")
                .fromJsonObject()
                .mappingInto(WeatherEntity.class)
                .execute(REQUEST_TAG, new RequestCallback<WeatherEntity>() {
                    @Override
                    public void onRequestSuccess(WeatherEntity objc) {

                        QuickUtils.log.i(objc.toString());

                        textView.setText(objc.toString());
                    }

                    @Override
                    public void onRequestError(RequestError error) {
                        QuickUtils.log.i("error " + error.getErrorCode());
                    }
                });
    }
}
