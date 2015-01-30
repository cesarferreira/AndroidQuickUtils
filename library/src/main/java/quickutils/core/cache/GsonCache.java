package quickutils.core.cache;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import quickutils.core.QuickUtils;
import quickutils.core.cache.interfaces.DeleteFromCacheCallback;
import quickutils.core.cache.interfaces.LoadFromCacheCallback;
import quickutils.core.cache.interfaces.SaveToCacheCallback;


public class GsonCache {

    private static SimpleDiskCache cache;
    private static long mMaxSize;
    private static Context mContext;

    /**
     * Initialize
     *
     * @param maxSize the maximum size in bytes.
     */
    public static synchronized void init(long maxSize) throws Exception {
        mContext = QuickUtils.getContext();
        mMaxSize = maxSize;
        cache = SimpleDiskCache.open(mContext.getFilesDir(), 1, mMaxSize);
    }

    /**
     * Check if an object with the given key exists in the Reservoir.
     *
     * @param key the key string.
     * @return true if object with given key exists.
     */
    public static boolean contains(String key) throws Exception {
        return cache.contains(key);
    }

    public static void deleteAll() throws Exception {
        cache.wipeData();
    }

    /**
     * Put an object into Reservoir with the given key. This a blocking IO operation. Previously
     * stored object with the same
     * key (if any) will be overwritten.
     *
     * @param key    the key string.
     * @param object the object to be stored.
     */
    public static void save(String key, Object object) throws Exception {
        String json = new Gson().toJson(object);
        cache.put(key, json);
    }

    /**
     * Put an object into Reservoir with the given key asynchronously. Previously
     * stored object with the same
     * key (if any) will be overwritten.
     *
     * @param key      the key string.
     * @param object   the object to be stored.
     * @param callback a callback of type {@link quickutils.core.cache.interfaces.SaveToCacheCallback
     *                 .ReservoirPutCallback} which is called upon completion.
     */
    public static void saveAsync(String key, Object object, SaveToCacheCallback callback) {
        new SaveTask(key, object, callback).execute();
    }

    /**
     * Get an object from Reservoir with the given key. This a blocking IO operation.
     *
     * @param key      the key string.
     * @param classOfT the Class type of the expected return object.
     * @return the object of the given type if it exists.
     */
    public static <T> T load(String key, Class<T> classOfT) throws Exception {

        String json = cache.getString(key).getString();
        T value = new Gson().fromJson(json, classOfT);
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    /**
     * Get an object from Reservoir with the given key asynchronously.
     *
     * @param key      the key string.
     * @param callback a callback of type {@link quickutils.core.cache.interfaces.LoadFromCacheCallback
     *                 .ReservoirGetCallback} which is called upon completion.
     */
    public static <T> void loadAsync(String key, Class<T> classOfT, LoadFromCacheCallback<T> callback) {
        new LoadTask<T>(key, classOfT, callback).execute();
    }

    public static <T> void loadAsync(String key, TypeToken typeToken, LoadFromCacheCallback<T> callback) {
        new LoadTask<T>(key, typeToken.getRawType(), callback).execute();
    }

    /**
     * Delete an object from Reservoir with the given key. This a blocking IO operation. Previously
     * stored object with the same
     * key (if any) will be deleted.
     *
     * @param key the key string.
     */
    public static void delete(String key) throws Exception {
        cache.delete(key);
    }

    /**
     * Delete an object into Reservoir with the given key asynchronously. Previously
     * stored object with the same
     * key (if any) will be deleted.
     *
     * @param key      the key string.
     * @param callback a callback of type {@link quickutils.core.cache.interfaces.DeleteFromCacheCallback
     *                 .ReservoirDeleteCallback} which is called upon completion.
     */
    public static void deleteAsync(String key, DeleteFromCacheCallback callback) {
        new DeleteTask(key, callback).execute();
    }

    /**
     * AsyncTask to perform put operation in a background thread.
     */
    private static class SaveTask extends AsyncTask<Void, Void, Void> {

        private final String key;
        private Exception e;
        private final SaveToCacheCallback callback;
        final Object object;

        private SaveTask(String key, Object object, SaveToCacheCallback callback) {
            this.key = key;
            this.callback = callback;
            this.object = object;
            this.e = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String json = new Gson().toJson(object);
                cache.put(key, json);
            } catch (Exception e) {
                this.e = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

    /**
     * AsyncTask to perform get operation in a background thread.
     */
    private static class LoadTask<T> extends AsyncTask<Void, Void, T> {

        private final String key;
        private final LoadFromCacheCallback callback;
        private final Class<T> classOfT;
        private Exception e;

        private LoadTask(String key, Class<T> classOfT, LoadFromCacheCallback callback) {
            this.key = key;
            this.callback = callback;
            this.classOfT = classOfT;
            this.e = null;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                String json = cache.getString(key).getString();
                T value = new Gson().fromJson(json, classOfT);
                if (value == null)
                    throw new NullPointerException();
                return value;
            } catch (Exception e) {
                this.e = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(T object) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess(object);
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

    /**
     * AsyncTask to perform delete operation in a background thread.
     */
    private static class DeleteTask extends AsyncTask<Void, Void, Void> {

        private final String key;
        private Exception e;
        private final DeleteFromCacheCallback callback;

        private DeleteTask(String key, DeleteFromCacheCallback callback) {
            this.key = key;
            this.callback = callback;
            this.e = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                cache.delete(key);
            } catch (Exception e) {
                this.e = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess();
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

}
