package com.starter.template.ui.splash;


import com.starter.template.ui.base.BaseContract;

final class SplashContract {
    interface SplashView extends BaseContract.BaseView {

        void launchLoginScreenWithDelay(int splashDelay);

        void launchHomeScreenWithDelay(int splashDelay);

        void showForceUpdateDialog();

        void launchPlayStoreToPackage();
    }

    interface SplashPresenter extends BaseContract.BasePresenter {

        void loadContent();

        void navigateToNextPage();

        void updateAppRequested();
    }
}
