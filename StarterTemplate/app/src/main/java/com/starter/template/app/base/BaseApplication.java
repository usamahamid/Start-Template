package com.starter.template.app.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.starter.template.app.analytics.AnalyticsDelegate;

public abstract class BaseApplication extends MultiDexApplication {

    private static BaseApplication application;
    private AnalyticsDelegate analyticsDelegate;

    public static BaseApplication getApplication() {
        return application;
    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        onAppCreate();
    }

    protected abstract void onAppCreate();

    protected void initAnalyticsDelegate() {
        analyticsDelegate = new AnalyticsDelegate(this);
    }

    public AnalyticsDelegate getAnalyticsDelegate() {
        return analyticsDelegate;
    }
}
