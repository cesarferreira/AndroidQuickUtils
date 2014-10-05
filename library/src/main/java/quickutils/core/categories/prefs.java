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

    public static prefs with(Context context) {
        if (singleton == null) {
            singleton = new Builder(context).build();
        }
        return singleton;
    }

    public void save(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public void save(String key, String value) {
        editor.putString(key, value).apply();
    }

    public void save(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public void save(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public void save(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public void remove(String key) {
        editor.remove(key).apply();
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

