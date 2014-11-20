package quickutils.core.rest;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;


public final class NetworkCreator {

    NetworkCreator() {
    }

    /**
     * @param headers Headers request, it can be null
     */
    public NetworkManager.INetworkManagerBuilder get(Header headers) {
        return new NetworkManager.Builder().setMethod(Request.Method.GET).setHeaders(headers.getHeaders());
    }

    /**
     *
     */
    public NetworkManager.INetworkManagerBuilder get() {
        return new NetworkManager.Builder().setMethod(Request.Method.GET);
    }

    /**
     * @param headers    Headers request, it can be null
     * @param bodyObject Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder post(Header headers, Object bodyObject) {
        return new NetworkManager.Builder().setMethod(Request.Method.POST).setBodyRequest(generateBodyRequest(bodyObject)).setHeaders(headers.getHeaders());
    }


    /**
     * @param headers     Headers request, it can be null
     * @param bodyRequest Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder post(Header headers, Body bodyRequest) {
        return new NetworkManager.Builder().setMethod(Request.Method.POST).setBodyRequest(bodyRequest.getBody()).setHeaders(headers.getHeaders());
    }

    /**
     * @param bodyObject Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder post(Object bodyObject) {
        return new NetworkManager.Builder().setMethod(Request.Method.POST).setBodyRequest(generateBodyRequest(bodyObject));
    }

    /**
     * @param bodyRequest Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder post(Body bodyRequest) {
        return new NetworkManager.Builder().setMethod(Request.Method.POST).setBodyRequest(bodyRequest.getBody());
    }

    /**
     * @param headers    Headers request, it can be null
     * @param bodyObject Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder put(Header headers, Object bodyObject) {
        return new NetworkManager.Builder().setMethod(Request.Method.PUT).setBodyRequest(generateBodyRequest(generateBodyRequest(bodyObject))).setHeaders(headers.getHeaders());
    }

    /**
     * @param headers     Headers request, it can be null
     * @param bodyRequest Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder put(Header headers, Body bodyRequest) {
        return new NetworkManager.Builder().setMethod(Request.Method.PUT).setBodyRequest(generateBodyRequest(bodyRequest.getBody())).setHeaders(headers.getHeaders());
    }

    /**
     * @param bodyObject Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder put(Object bodyObject) {
        return new NetworkManager.Builder().setMethod(Request.Method.PUT).setBodyRequest(generateBodyRequest(bodyObject));
    }

    /**
     * @param bodyRequest Body request, it not null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder put(Body bodyRequest) {
        return new NetworkManager.Builder().setMethod(Request.Method.PUT).setBodyRequest(bodyRequest.getBody());
    }

    /**
     * @param headers Headers request, it can be null
     * @return
     */
    public NetworkManager.INetworkManagerBuilder delete(Header headers) {
        return new NetworkManager.Builder().setMethod(Request.Method.DELETE).setHeaders(headers.getHeaders());
    }

    private HashMap<String, Object> generateBodyRequest(Object bodyRequest) {
        String bodyJson = new Gson().toJson(bodyRequest);
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        HashMap<String, Object> body = new Gson().fromJson(bodyJson, type);
        return body;
    }

}
