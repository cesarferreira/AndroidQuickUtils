package com.cesarferreira.quickutils.sample.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cesarferreira.quickutils.sample.R;

import quickutils.core.QuickUtils;
import quickutils.core.interfaces.OnEventListener;

public class BlurActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_blur);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void blurThisView(View view) {
        Bitmap mBitmap = QuickUtils.image.convertImageResourceToBitmap(R.drawable.image);
        imageView.setImageBitmap(QuickUtils.image.blur(mBitmap, 19));
    }

    public void blurRemoteImage(View view) {
        String imageURL = "http://cesarferreira.com/images/photo.jpg";
        QuickUtils.image.getBitmapByImageURL(imageURL, new OnEventListener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(QuickUtils.image.blur(bitmap, 19));
            }

            @Override
            public void onFailure(Exception e) {
                QuickUtils.system.toast("ERROR FETCHING IMAGE");
            }
        });


    }
}
