package com.starter.template.util.common;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.annotation.NonNull;

import com.starter.template.R;


/**
 * Utils class to work with new chrome custom tabs.
 * <p/>
 * Ref: https://developer.chrome.com/multidevice/android/custom tabs
 * <p/>
 * Created by gobinath on 07/03/16.
 */
public final class CustomTabUtils {
    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs" +
            ".extra" + ".TOOLBAR_COLOR";
    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra" +
            ".SESSION";
    public static final String EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE =
            "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";

    /**
     * Enables #EXTRA_CUSTOM_TABS_SESSION, sets address bar colour to color primary and sets
     * activity exit animation.
     *
     * @param context {@link Context}
     * @param intent  View {@link Intent}
     */
    public static void enableChromeCustomTab(@NonNull Context context, @NonNull Intent intent) {
        Bundle extras = new Bundle();
        extras.putBinder(EXTRA_CUSTOM_TABS_SESSION, null);
        int[] attrs = {R.attr.colorPrimary};
        TypedArray ta = context.obtainStyledAttributes(R.style.AppTheme, attrs);
        int themeColour = ta.getColor(0, context.getResources().getColor(R.color.colorPrimary));
        ta.recycle();

        Bundle finishBundle = CustomAnimationUtils.getSlideToRightFinishBundle(context);
        intent.putExtra(EXTRA_CUSTOM_TABS_EXIT_ANIMATION_BUNDLE, finishBundle);
        intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, themeColour);
        intent.putExtras(extras);
    }

    /**
     * Open a #EXTRA_CUSTOM_TABS_SESSION with the given link resource.
     *
     * @param context   {@link Context}
     * @param linkResId string resource id of the link
     */
    public static void openURIInCustomTabIfPossible(Context context, int linkResId) {
        openURIInCustomTabIfPossible(context, context.getString(linkResId));
    }

    /**
     * Open a #EXTRA_CUSTOM_TABS_SESSION with the given link resource.
     *
     * @param context   {@link Context}
     * @param uriString string uri
     */
    public static void openURIInCustomTabIfPossible(Context context, String uriString) {
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
        Bundle startBundle = CustomAnimationUtils.getSlideFromRightStartBundle(context);

        CustomTabUtils.enableChromeCustomTab(context, intent);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent, startBundle);
        }
    }

}
