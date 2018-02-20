package com.starter.template.util.common;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static com.starter.template.util.constants.AppConstants.SHORT_ANIMATION_DELAY;


public final class KeyboardUtils {
    public static int dismissKeyboardAndReturnDelay(View view) {
        if (dismissKeyboard(view)) {
            return SHORT_ANIMATION_DELAY;
        }
        return 0;
    }

    public static boolean dismissKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int showKeyboardAndReturnDelay(View view) {
        if (showKeyboard(view)) {
            return SHORT_ANIMATION_DELAY;
        }
        return 0;
    }

    public static boolean showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
