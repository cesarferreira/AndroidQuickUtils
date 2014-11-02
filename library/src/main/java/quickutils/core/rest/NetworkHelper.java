package quickutils.core.rest;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.TimeUnit;

public class NetworkHelper {
    private static final String TAG = NetworkHelper.class.getSimpleName();
    private static volatile NetworkHelper instance;

    public static NetworkHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (NetworkHelper.class) {
                if (instance == null) {
                    final NetworkHelper tmp = new NetworkHelper(context);
                    instance = tmp;
                }
            }
        }
        return instance;
    }

    private RequestQueue mRequestQueue;
    private final Context context;

    public NetworkHelper(Context context) {
        this.context = context;
    }

    public DefaultRetryPolicy retryPolicy() {
        return new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(20),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(retryPolicy());
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(retryPolicy());
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
