package com.starter.template.util.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * App preferences wrapper.
 */
public abstract class BaseAppPreferences {
    private static final String TAG = BaseAppPreferences.class.getSimpleName();
    private static final String PREFERENCE_FILE_NAME = "preference";

    // Key names.
    private static BaseAppPreferences instance;

    private final SharedPreferences sharedpreferences;


    protected BaseAppPreferences(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME,
                Context.MODE_PRIVATE);
    }

    /**
     * Returns preference string value if any, #defaultValue otherwise.
     *
     * @param tagName      preference name.
     * @param defaultValue default value
     */
    private String getString(String tagName, String defaultValue) {
        return sharedpreferences.getString(tagName, defaultValue);
    }

    /**
     * Returns preference string value if any, null otherwise.
     *
     * @param tag preference name.
     */
    public String getString(String tag) {
        return getString(tag, null);
    }

    /**
     * Deletes the entry for preferences.
     *
     * @param tag any tag.
     */
    public void removePref(String tag) {
        sharedpreferences.edit().remove(tag).apply();
    }

    /**
     * Returns integer preference value if any, #defaultValue otherwise.
     *
     * @param tagName      preference name
     * @param defaultValue default value
     */
    public int getInt(String tagName, int defaultValue) {
        return sharedpreferences.getInt(tagName, defaultValue);
    }

    /**
     * Returns integer preference value if any, -1 otherwise.
     *
     * @param tagName preference name
     */
    public int getInt(String tagName) {
        return sharedpreferences.getInt(tagName, -1);
    }

    /**
     * Inserts integer preference.
     *
     * @param tagName Preference name
     * @param value   value
     */
    public void putInt(String tagName, int value) {
        sharedpreferences.edit().putInt(tagName, value).apply();
    }

    public void putString(@NonNull String tagName, @NonNull String value) {
        sharedpreferences.edit().putString(tagName, value).apply();
    }

    public void putLong(@NonNull String tagName, long value) {
        sharedpreferences.edit().putLong(tagName, value).apply();
    }

    public long getLong(String key) {
        return sharedpreferences.getLong(key, -1);
    }

    public void putBoolean(String key, boolean value) {
        sharedpreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedpreferences.getBoolean(key, defaultValue);
    }

    public void remove(String tag) {
        sharedpreferences.edit().remove(tag).apply();
    }

    /**
     * Stores object with a key in file
     *
     * @param context
     * @param key
     * @param obj
     */
    public static void storeObject(Context context, String key, Object obj) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.writeObject(obj);
            out.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.w(TAG, "Could not store object [key = " + key + "]");
        }
    }

    /**
     * Deletes the object based on the key
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean deleteObject(Context context, String key) {
        return context.deleteFile(key);
    }

    /**
     * Gets the object from input stream based on the key passed
     *
     * @param context
     * @param key
     * @return
     */
    public static Object retrieveObject(Context context, String key) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        Object obj = null;
        try {
            fis = context.openFileInput(key);
            in = new ObjectInputStream(fis);
            obj = in.readObject();
            in.close();
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.w(TAG, "Could not restore object [key = " + key + "]");
        }

        return obj;
    }

    public void putDate(String tag, Date date) {
        sharedpreferences.edit().putLong(tag, date.getTime()).apply();
    }

    public Date getDate(String tag) {
        return new Date(sharedpreferences.getLong(tag, 0));
    }
}
