package com.starter.template.app;

import android.content.Context;

import com.starter.template.util.common.AESCrypt;
import com.starter.template.util.common.BaseAppPreferences;
import com.starter.template.util.constants.tags.AppPrefTags;

public class AppPreferences extends BaseAppPreferences {

    private static AppPreferences instance;

    private AppPreferences(Context context) {
        super(context);
    }

    /**
     * Returns app preference reference.
     */
    public synchronized static AppPreferences getPref(Context context) {
        if (instance == null) {
            instance = new AppPreferences(context);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        if (getUserName() != null && getPassword() != null) {
            return true;
        }
        clearUser();
        return false;
    }

    public void clearUser() {
        remove(AppPrefTags.USER_NAME);
        remove(AppPrefTags.PASSWORD);
    }

    public String getUserName() {
        return AESCrypt.decrypt(getString(AppPrefTags.USER_NAME));
    }

    public void setUserName(String username) {
        putString(AppPrefTags.USER_NAME, AESCrypt.encrypt(username));
    }

    public String getPassword() {
        return AESCrypt.decrypt(getString(AppPrefTags.PASSWORD));
    }

    public void setPassword(String password) {
        putString(AppPrefTags.PASSWORD, AESCrypt.encrypt(password));
    }
}
