package com.starter.template.network.base;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

public class StringRequest extends com.android.volley.toolbox.StringRequest {
    private Map<String, String> headers;
    private Map<String, String> params;
    private String body;

    public StringRequest(int method,
                         String url,
                         Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public StringRequest(int method,
                         String url,
                         String body,
                         Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.body = body;
    }

    public StringRequest(int method,
                         String url,
                         String body,
                         Map<String, String> headers,
                         Map<String, String> params,
                         Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.body = body;
        this.headers = headers;
        this.params = params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (headers != null && !headers.isEmpty()) {
            return headers;
        }
        return super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (params != null && !params.isEmpty()) {
            return params;
        }
        return super.getParams();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (!TextUtils.isEmpty(body)) {
            return body.getBytes();
        }
        return super.getBody();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String str = new String(response.data);
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
    }
}
