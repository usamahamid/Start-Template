package com.starter.template.network.request.base;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest {


    public BaseRequest() {
    }


    public Map<String, String> getHeader() {
        HashMap<String, String> header = new HashMap<>();
        return header;
    }

    public JSONObject getBody() {
        return new JSONObject();
    }
}
