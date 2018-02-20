package com.starter.template.util.constants;

public final class ApiConstants {

    public static final String API_FORMAT = "https://%s/";
    public static final String DOMAIN = "igw.vedantagroup.com:1443";
    public static final String BASE_API = String.format(API_FORMAT, DOMAIN);

    // ---------------------- API Request Tags ------------------------------

    public static final int REQUEST_TAG_AUTH_USER = 100;

}
