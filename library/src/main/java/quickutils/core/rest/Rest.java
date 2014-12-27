package quickutils.core.rest;

import quickutils.core.QuickUtils;

public class Rest {
    static Rest singleton = null;

    public static final String DEFAULT_TAG = "resttag";

    public Rest() {
    }

    public synchronized static Rest init() {
        if (singleton == null) {
            singleton = new Rest();
        }
        return singleton;
    }

    public synchronized static NetworkCreator connect() {
        if (singleton == null) {
            throw new IllegalArgumentException("QuickUtils not instantiated yet.");
        }
        return singleton.createRequest();
    }

    public void cancelRequests() {
        NetworkHelper.getInstance(QuickUtils.getContext()).cancelPendingRequests(Rest.DEFAULT_TAG);
    }

    public NetworkCreator createRequest() {
        return new NetworkCreator();
    }
}
