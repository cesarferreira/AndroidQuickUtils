package quickutils.core.rest;

import com.android.volley.NetworkResponse;

import java.util.Map;

import quickutils.core.QuickUtils;

public class RequestError extends Exception {
    public final static int REQUEST_RESPONSE_NOT_OK = -1;
    public final static int REQUEST_RESPONSE_OK = 200;
    public final static int REQUEST_RESPONSE_CREATED = 201;
    public final static int REQUEST_RESPONSE_ACCEPTED = 202;
    public final static int REQUEST_RESPONSE_NO_CONTENT = 204;
    public final static int REQUEST_RESPONSE_BAD_REQUEST = 400;
    public final static int REQUEST_RESPONSE_UNAUTHORIZED = 401;
    public final static int REQUEST_RESPONSE_FORBIDDEN = 403;
    public final static int REQUEST_RESPONSE_PAYMENT_REQUIRED = 402;
    public final static int REQUEST_RESPONSE_NOT_FOUND = 404;
    public final static int REQUEST_RESPONSE_GONE = 410;
    public final static int REQUEST_RESPONSE_UNPROCESSABLE_ENTITY = 422;
    public final static int REQUEST_RESPONSE_INTERNAL_SERVER_ERROR = 500;
    public final static int REQUEST_RESPONSE_SERVICE_UNAVAILABLE = 503;
    public final static int REQUEST_RESPONSE_MULTIPLE_DEVICE = 429;
    public final static int REQUEST_RESPONSE_NOT_PERMITTED = 301;
    public final static int REQUEST_RESPONSE_RESET_PASSWORD_SUCCESS = 204;

    final int errorCode;
    private String errorMessage;
    final Map<String, String> headers;

    RequestError(NetworkResponse response) {
        this.errorCode = response.statusCode;
        this.headers = response.headers;
        if(response.data != null)
            this.errorMessage = new String(response.data);
    }


    RequestError(String response) {
        this.errorCode = REQUEST_RESPONSE_NOT_OK;
        this.headers = null;
        this.errorMessage = response;
        QuickUtils.log.e("ATTENTION: " + this.errorMessage);
    }


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
