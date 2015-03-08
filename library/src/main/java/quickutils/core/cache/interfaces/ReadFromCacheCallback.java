package quickutils.core.cache.interfaces;

public interface ReadFromCacheCallback<T> {
    public void onSuccess(T object);

    public void onFailure(Exception e);
}
