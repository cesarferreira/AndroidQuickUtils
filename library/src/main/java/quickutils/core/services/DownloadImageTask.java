package quickutils.core.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import quickutils.core.QuickUtils;
import quickutils.core.interfaces.OnEventListener;

/**
 * Created by cesarferreira on 16/06/14.
 */
public class DownloadImageTask<T> extends AsyncTask<Void, Void, T> {

    protected final OnEventListener callback;
    private final String mImageURL;
    protected Exception e;
    protected Bitmap mBitmap;

    public DownloadImageTask(String imageURL, OnEventListener callback) {
        this.callback = callback;
        this.mImageURL = imageURL;
        this.e = null;
    }

    @Override
    protected T doInBackground(Void... params) {
        QuickUtils.log.i("BACKGROUND");
        URL url;

        try {
            url = new URL(mImageURL);
            mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e1) {
            e = e1;
        } catch (IOException e1) {
            e = e1;
        }


        return (T) mBitmap;
    }

    @Override
    protected void onPostExecute(T bitmap) {
        if (callback != null) {

            if (e == null && bitmap != null) {
                callback.onSuccess(bitmap);
            } else {
                callback.onFailure(e);
            }

        }
    }

}