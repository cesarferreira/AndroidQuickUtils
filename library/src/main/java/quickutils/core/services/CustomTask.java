package quickutils.core.services;

import android.os.AsyncTask;

import quickutils.core.OnEventListener;

/**
 * Created by cesarferreira on 16/06/14.
 */
public class CustomTask<T> extends AsyncTask<Void, Void, T> {

    protected final OnEventListener callback;
    protected final Class<T> classOfT;
    protected Exception e;

    public CustomTask(Class<T> classOfT, OnEventListener callback) {
        this.callback = callback;
        this.classOfT = classOfT;
        this.e = null;
    }

    @Override
    protected T doInBackground(Void... params) {
        try {
            //String json = cache.getString(key).getString();
            //return new Gson().fromJson(json, classOfT);
            return (T)"asd";
        } catch (Exception e) {
            this.e = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(T object) {
        if (callback != null) {
            if(e == null) {
                callback.onSuccess(object);
            }
            else {
                callback.onFailure(e);
            }
        }
    }

}