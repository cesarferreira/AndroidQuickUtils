package quickutils.core;


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
	public static final boolean PRODUCTION_MODE = false;

	public static boolean DEBUG_MODE = QuickUtils.DEVELOPER_MODE;

	/**
	 * private constructor
	 */
	private QuickUtils() {
	}

	/**
	 * Set debug mode (default: QuickUtils.DEBUG_MODE). Set
	 * QuickUtils.PRODUCTION_MODE when you go to production
	 * 
	 * @param debugMode
	 *            the new developer mode value
	 */
	public static void setDebugMode(boolean debugMode) {
		DEBUG_MODE = debugMode;
	}

	/**
	 * Set the log TAG for debug purposes
	 * 
	 * @param TAG
	 *            Desired tag (e.g.: Aplication_X)
	 */
	public static void setTAG(String tag) {
		TAG = tag;
	}

	/**
	 * Log Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class log extends quickutils.core.util.log.log {
	}

	/**
	 * Miscelaneous android App Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class misc extends quickutils.core.util.misc.misc {

	}

	/**
	 * Text library
	 * 
	 * @author Joel
	 * 
	 */
	public static class text extends quickutils.core.util.text.text {
	}

	/**
	 * Math library
	 * 
	 * @author cesar
	 * 
	 */
	public static class math extends quickutils.core.util.math.math {
	}

	/**
	 * Date Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class date extends quickutils.core.util.date.date {
	}

	/**
	 * Internet utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class web extends quickutils.core.util.web.web {
	}

	/**
	 * Share utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class share extends quickutils.core.util.share.share {
	}

	/**
	 * File Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class sdcard extends quickutils.core.util.sdcard.sdcard {
	}

	/**
	 * Security Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class security extends quickutils.core.util.security.security {
	}
	
	/**
	 * Security Utils
	 * 
	 * @author cesar
	 * 
	 */
	public static class voice extends quickutils.core.util.voice.voice {
	}
}
