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

    /**s
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
    public static synchronized void init(String TAG, boolean debugMode) {
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

    public static class log extends quickutils.core.log {}

    public static class system extends quickutils.core.system {}

    public static class text extends quickutils.core.text {}

    public static class math extends quickutils.core.math {}

    public static class date extends quickutils.core.date {}

    public static class web extends quickutils.core.web {}

    public static class share extends quickutils.core.share {}

    public static class sdcard extends quickutils.core.sdcard {}

    public static class security extends quickutils.core.security {}

    //todo public static class voice extends quickutils.core.voice {}

    public static class animation extends quickutils.core.animation {}

    public static class image extends quickutils.core.image {}

    public static class fragment extends quickutils.core.fragment {}

    public static class collection extends quickutils.core.collection {}

    public static class prefs extends Prefs {
        prefs(Context context) {
            super(context);
        }
    }

    public static class views extends view {}

}
