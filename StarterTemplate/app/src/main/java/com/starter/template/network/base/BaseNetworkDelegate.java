package com.starter.template.network.base;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.starter.template.network.response.base.BaseResponseHandler;
import com.starter.template.util.constants.AppConstants;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class BaseNetworkDelegate {

    private final BaseNetworkHelper networkHelper;


    public BaseNetworkDelegate(BaseNetworkHelper networkHelper) {
        this.networkHelper = networkHelper;

    }

    private RequestQueue getRequestQueue() {
        return networkHelper.getQueue();
    }

    protected void addToQueue(Request request) {
        getRequestQueue().add(request);
    }

    protected JsonRequest jsonGETRequest(
            int tag,
            String url,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                jsonRequest(
                        tag,
                        Request.Method.GET,
                        url,
                        Collections.<String, String>emptyMap(),
                        null,
                        baseResponseHandler);

        addToQueue(request);
        return request;
    }

    protected JsonRequest jsonGETRequest(
            int tag,
            String url,
            Map<String, String> headerMap,
            Map<String, String> params,
            BaseResponseHandler baseResponseHandler) {

        url = appendGETParamsToURL(params, url);
        JsonRequest request =
                jsonRequest(tag, Request.Method.GET, url, headerMap, null, baseResponseHandler);
        addToQueue(request);
        return request;
    }

    private String appendGETParamsToURL(Map<String, String> params, String url) {
        StringBuilder builder = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            builder.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.append(key).append("=").append(params.get(key)).append("&");
            }
            url = builder.toString().replace(" ", "%20");
        }
        return url;
    }

    protected JsonRequest jsonGETRequest(
            int tag,
            String url,
            Map<String, String> headerMap,
            BaseResponseHandler baseResponseHandler) {
        JsonRequest request =
                jsonRequest(tag, Request.Method.GET, url, headerMap, null, baseResponseHandler);
        addToQueue(request);
        return request;
    }

    protected JsonRequest jsonPOSTRequest(
            int tag,
            String url,
            Map<String, String> header,
            JSONObject jsonObject,
            String body,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                jsonRequest(
                        tag,
                        Request.Method.POST,
                        url,
                        header,
                        jsonObject,
                        body,
                        baseResponseHandler);

        addToQueue(request);
        return request;
    }

    protected JsonRequest jsonPOSTRequest(
            int tag,
            String url,
            Map<String, String> headerMap,
            JSONObject jsonObject,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                jsonRequest(tag, Request.Method.POST, url, headerMap, jsonObject, baseResponseHandler);
        addToQueue(request);
        return request;
    }

    protected JsonRequest jsonDELETERequest(
            int tag,
            String url,
            Map<String, String> headerMap,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                jsonRequest(tag, Request.Method.DELETE, url, headerMap, null, baseResponseHandler);
        addToQueue(request);
        return request;
    }

    private static JsonRequest jsonRequest(
            int tag,
            int method,
            String url,
            final Map<String, String> headerMap,
            JSONObject jsonObject,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                new JsonRequest(method, url, jsonObject, baseResponseHandler, baseResponseHandler) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return headerMap;
                    }
                };
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        AppConstants.NETWORK_TIMEOUT,
                        AppConstants.NETWORK_RETRY_COUNT,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(tag);

        return request;
    }

    private static JsonRequest jsonRequest(
            int tag,
            int method,
            String url,
            final Map<String, String> headerMap,
            JSONObject jsonObject,
            final String body,
            BaseResponseHandler baseResponseHandler) {

        JsonRequest request =
                new JsonRequest(method, url, jsonObject, baseResponseHandler, baseResponseHandler) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return headerMap;
                    }

                    @Override
                    public byte[] getBody() {
                        return body.getBytes();
                    }
                };
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        AppConstants.NETWORK_TIMEOUT,
                        AppConstants.NETWORK_RETRY_COUNT,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(tag);

        return request;
    }

    public void cancelAllRequestsWithTag(Object tag) {
        getRequestQueue().cancelAll(tag);
    }


    protected void sentStringRequest(StringRequest stringRequest) {
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        AppConstants.NETWORK_TIMEOUT,
                        AppConstants.NETWORK_RETRY_COUNT,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addToQueue(stringRequest);
    }
}
