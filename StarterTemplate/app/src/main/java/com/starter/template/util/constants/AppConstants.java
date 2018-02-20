package com.starter.template.util.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class AppConstants {

    public static final int LONG_ANIMATION_DELAY = 500;
    public static final int SHORT_ANIMATION_DELAY = 300;
    public static final int SPLASH_DELAY = 2000;

    public static final String ROOT_DIRECTORY = ".AscenteqStarRate";

    public static final String EMPTY_TEXT = "-";
    public static final String EMPTY = "";
    public static final int DEFAULT_SCORE_VALUE = 0;
    public static final int INVALID_ID = -1000002;
    public static final int INVALID_TIME = -1000002;

    // 4 hours in seconds
    public static final long REMOTE_CONFIG_CACHE_TIME = 14400;

    // ---------------------- Network Constants ------------------------------

    // Set 30 seconds as network timeout.
    public static final int NETWORK_TIMEOUT = 30000;
    public static final int NETWORK_RETRY_COUNT = 1;

    // ---------------------- Messages ------------------------------

    public static final String NO_NETWORK_MSG = "No Internet Connection.";
    public static final String TIME_OUT_MSG = "There seems to be a problem with internet connection. Please try again later.";
    public static final String GENERIC_NETWORK_ERROR_MSG = "Something went wrong. Please try again later.";
    public static final String INVALID_CREDENTIALS_MSG = "Invalid Credentials. Please try again.";

    // ---------------------- Date Constants ------------------------------

    public static final String DATE_FORMAT_STR = "dd-MM-yyyy";
    public static final String TIME_FORMAT_STR = "hh:mm a";
    public static final String DATETIME_FORMAT_STR = "MMM d, yyyy HH:mm";
    public static final long REFRESH_TOKEN_DIFF = 1000 * 60 * 25;

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STR, Locale.getDefault());
    public static final DateFormat TIME_FORMAT = new SimpleDateFormat(TIME_FORMAT_STR, Locale.getDefault());

}
