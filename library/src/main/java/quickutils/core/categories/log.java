package quickutils.core.categories;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import quickutils.core.QuickUtils;

public class log {

    /**
     * private constructor
     */
//	private log() {
//	}

    /**
     * Sends an ERROR log message
     *
     * @param message The message you would like logged.
     */
    public static int e(String message) {
        return logger(QuickUtils.ERROR, message);
    }

    /**
     * Sends an ERROR log message
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int e(String message, Throwable throwable) {
        return logger(QuickUtils.ERROR, message, throwable);
    }

    /**
     * Sends an INFO log message.
     *
     * @param message The message you would like logged.
     */
    public static int i(String message) {
        return logger(QuickUtils.INFO, message);
    }

    /**
     * Sends an INFO log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int i(String message, Throwable throwable) {
        return logger(QuickUtils.INFO, message, throwable);
    }

    /**
     * Sends a VERBBOSE log message.
     *
     * @param message The message you would like logged.
     */
    public static int v(String message) {
        return logger(QuickUtils.VERBOSE, message);
    }

    /**
     * Sends a VERBBOSE log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int v(String message, Throwable throwable) {
        return logger(QuickUtils.VERBOSE, message, throwable);
    }

    /**
     * Sends a WARNING log message.
     *
     * @param message The message you would like logged.
     */
    public static int w(String message) {
        return logger(QuickUtils.WARN, message);
    }

    /**
     * Sends a WARNING log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int w(String message, Throwable throwable) {
        return logger(QuickUtils.WARN, message, throwable);
    }

    /**
     * Sends a DEBUG log message.
     *
     * @param message The message you would like logged.
     */
    public static int d(String message) {
        return logger(QuickUtils.DEBUG, message);
    }

    /**
     * Sends a DEBUG log message and log the exception.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int d(String message, Throwable throwable) {
        return logger(QuickUtils.DEBUG, message, throwable);

    }

    /**
     * Private Logger function to handle Log calls
     *
     * @param level     level of the log message
     * @param message   log output
     * @param throwable
     */
    private static int logger(int level, String message, Throwable throwable) {

        if (QuickUtils.shouldShowLogs()) {
            switch (level) {

                case QuickUtils.DEBUG:
                    return android.util.Log.d(QuickUtils.TAG, message, throwable);
                case QuickUtils.VERBOSE:
                    return android.util.Log.v(QuickUtils.TAG, message, throwable);
                case QuickUtils.INFO:
                    return android.util.Log.i(QuickUtils.TAG, message, throwable);
                case QuickUtils.WARN:
                    return android.util.Log.w(QuickUtils.TAG, message, throwable);
                case QuickUtils.ERROR:
                    return android.util.Log.e(QuickUtils.TAG, message, throwable);
                default:
                    break;
            }
        }

        return -1;
    }

    /**
     * Private Logger function to handle Log calls
     *
     * @param level   level of the log message
     * @param message log output
     */
    private static int logger(int level, String message) {

        if (QuickUtils.shouldShowLogs()) {
            switch (level) {

                case QuickUtils.DEBUG:
                    return android.util.Log.d(QuickUtils.TAG, message);
                case QuickUtils.VERBOSE:
                    return android.util.Log.v(QuickUtils.TAG, message);
                case QuickUtils.INFO:
                    return android.util.Log.i(QuickUtils.TAG, message);
                case QuickUtils.WARN:
                    return android.util.Log.w(QuickUtils.TAG, message);
                case QuickUtils.ERROR:
                    return android.util.Log.e(QuickUtils.TAG, message);
                default:
                    break;
            }
        }

        return -1;
    }

    public static void staticFields(Class<?> clazz) throws IllegalAccessException {
        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) {
                boolean wasAccessible = f.isAccessible();
                f.setAccessible(true);
                QuickUtils.log.i(f.getName() + ": " + f.get(null));
                f.setAccessible(wasAccessible);
            }
        }
    }

    public static void privateFields(Class<?> clazz) throws IllegalAccessException {
        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isPrivate(f.getModifiers())) {
                boolean wasAccessible = f.isAccessible();
                f.setAccessible(true);
                QuickUtils.log.i(f.getName() + ": " + f.get(null));
                f.setAccessible(wasAccessible);
            }
        }
    }
}
