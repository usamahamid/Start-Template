package com.starter.template.util.common;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.starter.template.R;


public final class CustomAnimationUtils {

    public static void finishWithSlideToRight(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(
                R.anim.slide_in_from_left,
                R.anim.slide_out_through_right);
    }

    public static void finishWithFadeOut(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out);
    }


    public static Bundle getFadeInStartBundle(Context context) {
        return ActivityOptions.makeCustomAnimation(
                context,
                android.R.anim.fade_in,
                android.R.anim.fade_out).toBundle();
    }

    public static Bundle getSlideFromRightStartBundle(Context context) {
        return ActivityOptions.makeCustomAnimation(
                context,
                R.anim.slide_in_from_right,
                R.anim.slide_out_through_left).toBundle();
    }

    public static Bundle getSlideInFromBottomStartBundle(Context context) {
        return ActivityOptions.makeCustomAnimation(
                context,
                R.anim.slide_in_from_bottom,
                R.anim.slide_out_through_bottom).toBundle();
    }

    public static Bundle getSlideToRightFinishBundle(@NonNull Context context) {
        return ActivityOptions.makeCustomAnimation(
                context,
                R.anim.slide_in_from_left,
                R.anim.slide_out_through_right).toBundle();
    }
}
