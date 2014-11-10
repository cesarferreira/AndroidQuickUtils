package quickutils.core;


import android.content.Context;

import quickutils.core.categories.screen;
import quickutils.core.categories.string;
import quickutils.core.categories.validation;
import quickutils.core.categories.view;
import quickutils.core.exceptions.InitNotSetException;
import quickutils.core.rest.Rest;

public abstract class QuickUtils {

    public static String TAG = "REPLACE_WITH_DESIRED_TAG";

    private static Context mContext;

    public static final int VERBOSE = android.util.Log.VERBOSE;
    public static final int DEBUG = android.util.Log.DEBUG;
    public static final int INFO = android.util.Log.INFO;
    public static final int WARN = android.util.Log.WARN;
    public static final int ERROR = android.util.Log.ERROR;

    /**
     * Developer mode for Debugging purposes
     */
    private static Boolean showLogs = null;


    /**
     * private constructor
     */
    private QuickUtils() {
    }

    /**
     * Getter for the context
     *
     * @return the context
     */
    public static Context getContext() {

        if (mContext == null) {
            throw new InitNotSetException();
        }
        return mContext;
    }


    /**
     * Initialize QuickUtils
     */
    public static synchronized void init(Context context) {
        mContext = context;

        // Set the appropriate log TAG
        setTAG(QuickUtils.system.getApplicationNameByContext());

        // resets all timestamps
        QuickUtils.timer.resetAllTimestamps();

        // init Rest
        QuickUtils.rest.init();
    }

    /**
     * Set the log TAG for debug purposes
     *
     * @param tag Desired tag (e.g.: Aplication_X)
     */
    public static void setTAG(String tag) {
        TAG = tag;
    }

    /**
     * Should I show logs?
     *
     * @return true if you should
     */
    public static Boolean shouldShowLogs() {

        if (showLogs == null) {
            showLogs = QuickUtils.system.isDebuggable();
        }
        return showLogs;
    }


    public static class log extends quickutils.core.categories.log {
    }

    public static class system extends quickutils.core.categories.system {
    }

    public static class text extends quickutils.core.categories.text {
    }

    public static class math extends quickutils.core.categories.math {
    }

    public static class date extends quickutils.core.categories.date {
    }

    public static class web extends quickutils.core.categories.web {
    }

    public static class share extends quickutils.core.categories.share {
    }

    public static class sdcard extends quickutils.core.categories.sdcard {
    }

    public static class security extends quickutils.core.categories.security {
    }

    public static class animation extends quickutils.core.categories.animation {
    }

    public static class image extends quickutils.core.categories.image {
    }

    public static class collection extends quickutils.core.categories.collection {
    }

    public static class prefs extends quickutils.core.categories.prefs {
        prefs(Context context) {
            super(context);
        }
    }

    public static class views extends quickutils.core.categories.view {
    }

    public static class timer extends quickutils.core.categories.timer {}

    public static class string extends quickutils.core.categories.string {}

    public static class validation extends quickutils.core.categories.validation {}

    public static class screen extends quickutils.core.categories.screen {}

    public static class rest extends Rest {
        public rest() {
            super();
        }
    }
}
