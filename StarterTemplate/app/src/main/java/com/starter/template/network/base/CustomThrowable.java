package com.starter.template.network.base;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.starter.template.util.constants.AppConstants;

public class CustomThrowable extends Throwable {


    public CustomThrowable(String msg) {
        super(msg, new Throwable(AppConstants.GENERIC_NETWORK_ERROR_MSG));
    }

    public CustomThrowable(String msg, VolleyError error) {
        super(msg, error);
    }

    public boolean isAuthError() {
        return (getCause() instanceof AuthFailureError);
    }
}
