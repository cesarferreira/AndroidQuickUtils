package quickutils.core.cache.interfaces;

public interface LoadFromCacheCallback<T> {
    public void onSuccess(T object);

    public void onFailure(Exception e);
}
