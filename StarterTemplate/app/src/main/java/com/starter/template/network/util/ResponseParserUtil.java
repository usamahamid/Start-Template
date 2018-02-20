package com.starter.template.network.util;

import android.text.TextUtils;

import com.starter.template.util.common.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public final class ResponseParserUtil {
    public static final String DATE_TIME_FORMAT_STR = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_STR, Locale.getDefault());

    /**
     * Returns the value from json object
     */
    public static String getStringValue(JSONObject jsonObject, String tag) throws JSONException {
        String empty = "";
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return empty;
        }
        String value = jsonObject.getString(tag);
        if (TextUtils.isEmpty(value)) {
            return empty;
        }
        return value;
    }

    /**
     * Returns the value from json object
     */
    public static Double getDoubleValue(JSONObject jsonObject, String tag) throws JSONException {
        Double empty = 0d;
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return empty;
        }
        Object value = jsonObject.get(tag);
        if (value == null || !StringUtils.isNumeric(value.toString())) {
            return empty;
        }
        return jsonObject.getDouble(tag);
    }

    /**
     * Returns the long value from json object
     */
    public static long getLongValue(JSONObject jsonObject, String tag) throws JSONException {
        Long empty = 0L;
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return empty;
        }
        Object value = jsonObject.get(tag);
        if (value == null || !(value instanceof Long)) {
            return empty;
        }
        return (Long) value;
    }

    /**
     * Returns the Date value of format {@link ResponseParserUtil.DATE_TIME_FORMAT_STR} from json object
     */
    public static Date getDateValue(JSONObject jsonObject, String KEY) throws JSONException {
        String dateStr = getStringValue(jsonObject, KEY);
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
            return DATE_TIME_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns the integer value from json object
     */
    public static int getIntValue(JSONObject jsonObject, String tag) throws JSONException {
        Integer empty = 0;
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return empty;
        }
        Object value = jsonObject.get(tag);
        if (value == null || !(value instanceof Integer)) {
            return empty;
        }
        return (Integer) value;
    }

    /**
     * Returns the boolean value from json object
     */
    public static boolean getBooleanValue(JSONObject jsonObject, String tag) throws JSONException {
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return false;
        }
        return jsonObject.getBoolean(tag);
    }

    /**
     * Returns the {@link JSONObject} from json object
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String tag) throws JSONException {
        JSONObject empty = new JSONObject();
        if (!jsonObject.has(tag) || jsonObject.isNull(tag)) {
            return empty;
        }
        Object value = jsonObject.get(tag);
        if (value == null || !(value instanceof JSONObject)) {
            return empty;
        }
        return (JSONObject) value;
    }

    /**
     * Returns the {@link JSONArray} from json object
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String tag) throws JSONException {
        JSONArray empty = new JSONArray();
        if (!jsonObject.has(tag)) {
            return empty;
        }
        Object value = jsonObject.get(tag);
        if (value == null || !(value instanceof JSONArray)) {
            return empty;
        }
        return (JSONArray) value;
    }
}
