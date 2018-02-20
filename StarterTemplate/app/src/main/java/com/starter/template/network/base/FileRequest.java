package com.starter.template.network.base;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

public class FileRequest extends Request<byte[]> {
    private final Response.Listener<byte[]> mListener;


    public FileRequest(int method,
                       String mUrl,
                       Response.Listener<byte[]> listener,
                       Response.ErrorListener errorListener) {
        super(method, mUrl, errorListener);

        // this request would never use cache.
        setShouldCache(false);
        mListener = listener;
    }

    @Override
    protected void deliverResponse(byte[] response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        //Pass the response data here
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
}
