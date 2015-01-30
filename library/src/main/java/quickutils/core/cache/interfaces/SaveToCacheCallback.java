package quickutils.core.cache.interfaces;

public interface SaveToCacheCallback {
    public void onSuccess();

    public void onFailure(Exception e);
}
