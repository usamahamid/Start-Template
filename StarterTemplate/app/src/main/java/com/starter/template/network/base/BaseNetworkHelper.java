package com.starter.template.network.base;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public abstract class BaseNetworkHelper {

    protected RequestQueue queue;

    protected BaseNetworkHelper(Context context) {
    }

    public void initRequestQueue(Context context) {
        queue = Volley.newRequestQueue(context);
        queue.start();
    }

    public RequestQueue getQueue() {
        return queue;
    }

}
