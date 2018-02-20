package com.starter.template.ui.splash;


import com.starter.template.app.AppPreferences;
import com.starter.template.app.ForceUpdateDelegate;

import static com.starter.template.util.constants.AppConstants.SPLASH_DELAY;

class SplashPresenterImpl implements SplashContract.SplashPresenter {

    private final SplashContract.SplashView view;
    private final AppPreferences pref;
    private final ForceUpdateDelegate forceUpdateDelegate;

    SplashPresenterImpl(SplashContract.SplashView view) {
        this.view = view;
        pref = AppPreferences.getPref(view.getViewContext());
        forceUpdateDelegate = new ForceUpdateDelegate();
    }

    @Override
    public void loadContent() {
        if (forceUpdateDelegate.shouldForceUpdate()) {
            view.showForceUpdateDialog();
        } else {
            navigateToNextPage();
        }
    }

    @Override
    public void navigateToNextPage() {
        boolean loggedIn = pref.isLoggedIn();
        if (loggedIn) {
            view.launchHomeScreenWithDelay(SPLASH_DELAY);
        } else {
            view.launchLoginScreenWithDelay(SPLASH_DELAY);
        }
    }

    @Override
    public void updateAppRequested() {
        view.launchPlayStoreToPackage();
    }
}
