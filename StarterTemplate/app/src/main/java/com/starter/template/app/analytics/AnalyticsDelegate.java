package com.starter.template.app.analytics;

import android.content.Context;

import com.starter.template.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AnalyticsDelegate {

    private static GoogleAnalytics mAnalytics;
    private static Tracker mTracker;

    public AnalyticsDelegate(Context context) {
        mAnalytics = GoogleAnalytics.getInstance(context);
        mTracker = mAnalytics.newTracker(R.xml.analytics_tracker);
        mTracker.enableAutoActivityTracking(true);
        mTracker.enableExceptionReporting(true);
    }

    public static Tracker getDefaultTracker() {
        return mTracker;
    }

    public static GoogleAnalytics getAnalytics() {
        return mAnalytics;
    }

    public static void dispatchLocalHits() {
        mAnalytics.dispatchLocalHits();
    }


    public static void logAboutScreenLanding() {
        if (mTracker != null) {
            mTracker.setScreenName("StarRate About");
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }
}

