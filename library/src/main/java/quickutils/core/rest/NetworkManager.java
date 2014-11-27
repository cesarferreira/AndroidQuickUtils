package quickutils.core.rest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import quickutils.core.QuickUtils;
import quickutils.core.interfaces.RequestCallback;

public final class NetworkManager {

    public enum RESULT {
        JSONOBJECT,
        JSONARRAY
    }

    private final NetworkHelper networkHelper;
    private final String pathUrl;
    private final int method;
    private final TypeToken<?> classTarget;
    private final RESULT resultType;
    private final HashMap<String, Object> bodyRequest;
    private final HashMap<String, String> headers;

    public NetworkManager(Builder builder) {
        this.networkHelper = NetworkHelper.getInstance(QuickUtils.getContext());
        this.pathUrl = builder.pathUrl;
        this.method = builder.method;
        this.classTarget = builder.targetType;
        this.resultType = builder.resultType;
        this.bodyRequest = builder.bodyRequest;
        this.headers = builder.headers;
    }

    private String getUrlConnection(String pathUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append(pathUrl);

        return builder.toString();
    }

    private JSONObject createBodyRequest(HashMap<String, Object> bodyRequest) {
        return bodyRequest == null ? null : new JSONObject(bodyRequest);
    }

    private void fromJsonObject(final HashMap<String, String> headers, HashMap<String, Object> bodyRequest, String requestTag, final RequestCallback requestCallback) {
        JsonObjectRequest request = new JsonObjectRequest(method, getUrlConnection(pathUrl), createBodyRequest(bodyRequest), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Object t = new Gson().fromJson(jsonObject.toString(), classTarget.getType());
                if (requestCallback != null)
                    requestCallback.onRequestSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (requestCallback != null) {
                    NetworkResponse response = error.networkResponse;
                    if (response != null)
                        requestCallback.onRequestError(new RequestError(response));
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };

        networkHelper.addToRequestQueue(request, requestTag);
    }

    private void fromJsonArray(final Map<String, String> headers, String requestTag, final RequestCallback requestCallback) {
        JsonArrayRequest request = new JsonArrayRequest(getUrlConnection(pathUrl), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Object t = new Gson().fromJson(jsonArray.toString(), classTarget.getType());
                if (requestCallback != null)
                    requestCallback.onRequestSuccess(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (requestCallback != null) {
                    NetworkResponse response = error.networkResponse;
                    if (response != null) {
                        requestCallback.onRequestError(new RequestError(response));
                    } else {
                        requestCallback.onRequestError(new RequestError(error.toString()));

                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };

        networkHelper.addToRequestQueue(request, requestTag);
    }

    public void execute(String requestTag, RequestCallback callback) {
        if (resultType == null) {
            throw new IllegalArgumentException("result type must not be null.");
        }

        if (classTarget == null) {
            throw new IllegalArgumentException("class target must not be null.");
        }

        if (pathUrl == null) {
            throw new IllegalArgumentException("path url must not be null.");
        }

        switch (resultType) {
            case JSONARRAY:
                fromJsonArray(headers, requestTag, callback);
                break;
            case JSONOBJECT:
                if (method == Request.Method.POST)
                    if (bodyRequest == null)
                        throw new IllegalArgumentException("body request url must not be null.");

                fromJsonObject(headers, bodyRequest, requestTag, callback);
                break;
            default:
                throw new IllegalArgumentException("response type not found");
        }
    }

    public static class Builder implements INetworkManagerBuilder {
        private String pathUrl;
        private int method;
        private RESULT resultType;
        private TypeToken<?> targetType;
        private HashMap<String, Object> bodyRequest;
        private HashMap<String, String> headers;

        public Builder setMethod(int method) {
            this.method = method;
            return this;
        }

        public Builder setBodyRequest(HashMap<String, Object> bodyRequest) {
            this.bodyRequest = bodyRequest;
            return this;
        }

        public Builder setHeaders(HashMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        @Override
        public INetworkManagerBuilder pathUrl(String pathUrl) {
            this.pathUrl = pathUrl;
            return this;
        }

        @Override
        public INetworkManagerBuilder fromJsonObject() {
            this.resultType = RESULT.JSONOBJECT;
            return this;
        }

        @Override
        public INetworkManagerBuilder fromJsonArray() {
            this.resultType = RESULT.JSONARRAY;
            return this;
        }

        @Override
        public NetworkManager mappingInto(Class classTarget) {
            this.targetType = TypeToken.get(classTarget);
            return new NetworkManager(this);
        }

        @Override
        public NetworkManager mappingInto(TypeToken typeToken) {
            this.targetType = typeToken;
            return new NetworkManager(this);
        }
    }

    public static interface INetworkManagerBuilder {
        /**
         * @param pathUrl
         * @return
         */
        public INetworkManagerBuilder pathUrl(String pathUrl);

        public INetworkManagerBuilder fromJsonObject();

        public INetworkManagerBuilder fromJsonArray();

        public NetworkManager mappingInto(Class classTarget);

        public NetworkManager mappingInto(TypeToken typeToken);
    }
}
