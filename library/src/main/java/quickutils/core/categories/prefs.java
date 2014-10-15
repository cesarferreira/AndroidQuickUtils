package quickutils.core.categories;

import android.content.Context;
import android.content.SharedPreferences;

import quickutils.core.QuickUtils;

/**
 * Created by cesarferreira on 9/26/14.
 */
public class prefs {

    static prefs singleton = null;

    static SharedPreferences preferences;

    static SharedPreferences.Editor editor;

    protected prefs(Context context) {
        preferences = context.getSharedPreferences(QuickUtils.TAG, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private static prefs with(Context context) {
        if (singleton == null) {
            singleton = new Builder(context).build();
        }
        return singleton;
    }

    public static void save(String key, boolean value) {
        with(QuickUtils.getContext()).editor.putBoolean(key, value).apply();
    }

    public void save(String key, String value) {
        with(QuickUtils.getContext()).editor.putString(key, value).apply();
    }

    public static void save(String key, int value) {
        with(QuickUtils.getContext()).editor.putInt(key, value).apply();
    }

    public void save(String key, float value) {
        with(QuickUtils.getContext()).editor.putFloat(key, value).apply();
    }

    public void save(String key, long value) {
        with(QuickUtils.getContext()).editor.putLong(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return  with(QuickUtils.getContext()).preferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return  with(QuickUtils.getContext()).preferences.getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return  with(QuickUtils.getContext()).preferences.getInt(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return  with(QuickUtils.getContext()).preferences.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public void remove(String key) {
        with(QuickUtils.getContext()).editor.remove(key).apply();
    }

    /**
     * Builder class
     */
    private static class Builder {

        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Method that creates an instance of Prefs
         *
         * @return an instance of Prefs
         */
        public prefs build() {
            return new prefs(context);
        }
    }
}

