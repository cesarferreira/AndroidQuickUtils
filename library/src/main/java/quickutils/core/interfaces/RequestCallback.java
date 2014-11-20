package quickutils.core.interfaces;

import quickutils.core.rest.RequestError;

public interface RequestCallback<T> {
    public void onRequestSuccess(T t);

    public void onRequestError(RequestError error);
}