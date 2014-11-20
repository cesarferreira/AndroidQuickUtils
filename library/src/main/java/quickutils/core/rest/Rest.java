package quickutils.core.rest;

import quickutils.core.QuickUtils;

public class Rest {
    static Rest singleton = null;

    public Rest() {
    }

    public synchronized static Rest init() {
        if (singleton == null) {
            singleton = new Rest();
        }
        return singleton;
    }

    public synchronized static Rest connect() {
        if (singleton == null) {
            throw new IllegalArgumentException("QuickUtils not instantiated yet.");
        }
        return singleton;
    }

    public void cancelRequest(String tag) {
        NetworkHelper.getInstance(QuickUtils.getContext()).cancelPendingRequests(tag);
    }

    public NetworkCreator createRequest() {
        return new NetworkCreator();
    }
}
