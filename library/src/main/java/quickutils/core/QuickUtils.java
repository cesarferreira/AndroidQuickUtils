package quickutils.core;


import android.content.Context;

public abstract class QuickUtils {

    public static String TAG = "DESIRED_TAG";

    public static final int VERBOSE = android.util.Log.VERBOSE;
    public static final int DEBUG = android.util.Log.DEBUG;
    public static final int INFO = android.util.Log.INFO;
    public static final int WARN = android.util.Log.WARN;
    public static final int ERROR = android.util.Log.ERROR;

    public static final int REQUEST_CODE = 1234;
    public static final int RESULT_OK = 1234;

    /**
     * Developer mode for Debugging purposes
     */
    public static final boolean DEVELOPER_MODE = true;

    /**
     * Production mode for Debugging purposes
     */
    public static final boolean PRODUCTION_MODE = !DEVELOPER_MODE;

    public static boolean DEBUG_MODE = QuickUtils.DEVELOPER_MODE;

    /**
     * private constructor
     */
    private QuickUtils() {
    }

    /**
     * Initialize QuickUtils
     *
     */
    public static synchronized void init(String TAG, boolean debugMode) throws Exception {
        setDebugMode(debugMode);
        setTAG(TAG);

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

    /**
     * Log Utils
     *
     * @author cesar
     */
    public static class log extends quickutils.core.log {
    }

    /**
     * Miscelaneous android App Utils
     *
     * @author cesar
     */
    public static class system extends quickutils.core.system {

    }

    /**
     * Text library
     *
     * @author Joel
     */
    public static class text extends quickutils.core.text {
    }

    /**
     * Math library
     *
     * @author cesar
     */
    public static class math extends quickutils.core.math {
    }

    /**
     * Date Utils
     *
     * @author cesar
     */
    public static class date extends quickutils.core.date {
    }

    /**
     * Internet utils
     *
     * @author cesar
     */
    public static class web extends quickutils.core.web {
    }

    /**
     * Share utils
     *
     * @author cesar
     */
    public static class share extends quickutils.core.share {
    }

    /**
     * File Utils
     *
     * @author cesar
     */
    public static class sdcard extends quickutils.core.sdcard {
    }

    /**
     * Security Utils
     *
     * @author cesar
     */
    public static class security extends quickutils.core.security {
    }

    /**
     * Voice Utils
     *
     * @author cesar
     */
    public static class voice extends quickutils.core.voice {
    }


    /**
     * Animation Utils
     *
     * @author cesar
     */
    public static class animation extends quickutils.core.animation {
    }

    /**
     * Animation Utils
     *
     * @author cesar
     */
    public static class image extends quickutils.core.image {
    }
}
