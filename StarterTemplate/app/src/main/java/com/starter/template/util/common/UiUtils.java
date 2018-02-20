package com.starter.template.util.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.starter.template.R;


public final class UiUtils {

    private static final int SNACK_MAX_LINES = 4;

    public static Snackbar longSnackBar(View view, int msgRes) {
        Snackbar snackbar = Snackbar.make(view, msgRes, Snackbar.LENGTH_LONG);
        showSnack(snackbar);
        return snackbar;
    }

    private static void showSnack(Snackbar snackbar) {
        TextView textView =
                (TextView) snackbar
                        .getView()
                        .findViewById(android.support.design.R.id.snackbar_text);

        textView.setMaxLines(SNACK_MAX_LINES);
        snackbar.show();
    }

    public static Snackbar longSnackBar(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        showSnack(snackbar);
        return snackbar;
    }

    public static Snackbar longSnackBarWithSettingsTag(View view, int msgRes) {
        return longSnackBarWithTag(view, msgRes, R.string.settings, new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                startInstalledAppDetailsActivity(view1.getContext());
            }
        });
    }

    public static Snackbar longSnackBarWithTag(View view, String msg, int buttonTextID, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.setAction(buttonTextID, listener);
        showSnack(snackbar);
        return snackbar;
    }

    public static Snackbar longSnackBarWithTag(View view, int msgRes, int buttonTextID, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msgRes, Snackbar.LENGTH_LONG);
        snackbar.setAction(buttonTextID, listener);
        showSnack(snackbar);
        return snackbar;
    }

    public static Snackbar indefiniteSnackBarWithTag(View view, String msg, int buttonTextID, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(buttonTextID, listener);
        showSnack(snackbar);
        return snackbar;
    }

    public static Snackbar indefiniteSnackBarWithTag(View view, int msgRes, int buttonTextID, View.OnClickListener listener) {
        Snackbar snackbar = Snackbar.make(view, msgRes, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(buttonTextID, listener);
        showSnack(snackbar);
        return snackbar;
    }

    public static void startInstalledAppDetailsActivity(final Context context) {
        if (context == null) {
            return;
        }
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public static void openAppInStore(Context context, String appPackageName) {
        if (TextUtils.isEmpty(appPackageName)) {
            appPackageName = context.getPackageName();
        }

        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)), CustomAnimationUtils.getSlideFromRightStartBundle(context));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void openAppInStore(Context context) {
        openAppInStore(context, null);
    }
}
