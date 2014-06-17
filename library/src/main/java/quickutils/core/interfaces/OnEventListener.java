package quickutils.core.interfaces;

/**
 * Created by cesarferreira on 16/06/14.
 */
public interface OnEventListener<T> {
    public void onSuccess(T object);
    public void onFailure(Exception e);
}