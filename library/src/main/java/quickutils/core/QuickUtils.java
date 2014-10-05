package quickutils.core;


import android.content.Context;

import quickutils.core.categories.view;
import quickutils.core.exceptions.InitNotSetException;

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
    public static final boolean DEVELOPER_MODE = true;

    /**
     * s
     * Production mode for Debugging purposes
     */
    public static final boolean PRODUCTION_MODE = !DEVELOPER_MODE;

    public static boolean DEBUG_MODE = QuickUtils.DEVELOPER_MODE;

    /**
     * private constructor
     */
    private QuickUtils() {
    }

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
        setTAG(QuickUtils.system.getApplicationNameByContext());
    }


    /**
     * Set debug mode (default: QuickUtils.DEBUG_MODE). Set
     * QuickUtils.PRODUCTION_MODE when you go to production
     *
     * @param debugMode the new developer mode value
     */
    public static void setDebugMode(boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    /**
     * Set the log TAG for debug purposes
     *
     * @param tag Desired tag (e.g.: Aplication_X)
     */
    public static void setTAG(String tag) {
        TAG = tag;
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

    public static class fragment extends quickutils.core.categories.fragment {
    }

    public static class collection extends quickutils.core.categories.collection {
    }

    public static class prefs extends quickutils.core.categories.prefs {
        prefs(Context context) {
            super(context);
        }
    }

    public static class views extends view {
    }

}
