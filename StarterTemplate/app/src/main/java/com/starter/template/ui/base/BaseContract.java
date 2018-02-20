package com.starter.template.ui.base;

import android.content.Context;

public final class BaseContract {
    public interface BaseView {
        void showNetworkProgress();

        void dismissNetworkProgress();

        void showNetworkUnAvailableAlert();

        boolean isNetworkAvailable();

        Context getViewContext();

        boolean isActive();

        void showToast(String message);

        void showSnack(String message);

        void showSnack(int strResId);

        void finishAndGoBack();
    }

    public interface BasePresenter {
    }
}
