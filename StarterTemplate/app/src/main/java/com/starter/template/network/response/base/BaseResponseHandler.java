package com.starter.template.network.response.base;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.starter.template.network.base.CustomThrowable;
import com.starter.template.network.request.base.BaseRequest;

import java.util.Map;

import static com.starter.template.util.constants.AppConstants.GENERIC_NETWORK_ERROR_MSG;
import static com.starter.template.util.constants.AppConstants.TIME_OUT_MSG;


public abstract class BaseResponseHandler<T> implements Response.Listener<T>, Response.ErrorListener {

    private final BaseRequest baseRequest;
    public static final String TAG = "BaseResponseHandler";

    public BaseResponseHandler(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    @Override
    public void onResponse(T response) {
        try {
            onResponseReceived(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            onFailure(exception);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error.networkResponse != null && error.networkResponse.data != null) {
            Map<String, String> headers = error.networkResponse.headers;
        }

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            throwFailure(new CustomThrowable(TIME_OUT_MSG, error));
            return;
        }
        throwFailure(new CustomThrowable(GENERIC_NETWORK_ERROR_MSG, error));
    }

    private void throwFailure(Throwable throwable) {
        onFailure(throwable);
    }

    protected abstract void onResponseReceived(T response) throws Exception;

    public abstract void onFailure(Throwable cause);

}
