package com.cesarferreira.quickutils.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import quickutils.core.QuickUtils;
import quickutils.core.interfaces.OnEventListener;

public class TestingBlur extends Activity {

    private ImageView imageView;
    private ImageView smalliImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_blur);
        imageView = (ImageView) findViewById(R.id.imageView);
        smalliImageView = (ImageView) findViewById(R.id.smallImageView);
    }


    public void blurShitUp(View view) {
        QuickUtils.log.d("starting to do shit");

        Bitmap mBitmap = QuickUtils.image.convertImageResourceToBitmap(getApplicationContext(), R.drawable.image);

        imageView.setImageBitmap(QuickUtils.image.blur(getApplicationContext(), mBitmap, 19));
    }

    public void blurRemoteImage(View view) {
        String imageURL = "https://scontent-a-mad.xx.fbcdn.net/hphotos-xaf1/t1.0-9/10255937_10152406143734711_2499661729438789343_n.jpg";
        QuickUtils.image.getBitmapByImageURL(imageURL, new OnEventListener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(QuickUtils.image.blur(getApplicationContext(), bitmap, 19));
            }

            @Override
            public void onFailure(Exception e) {
                QuickUtils.system.toast(getApplicationContext(), "ERROR FETCHING IMAGE");
            }
        });


    }
}
