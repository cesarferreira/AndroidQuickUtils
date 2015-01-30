package quickutils.core.cache.interfaces;

public interface DeleteFromCacheCallback {
    public void onSuccess();

    public void onFailure(Exception e);
}
