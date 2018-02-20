package com.starter.template.ui.splash;

import android.os.Bundle;

import com.starter.template.R;
import com.starter.template.ui.base.BaseActivity;
import com.starter.template.ui.home.HomeActivity;
import com.starter.template.ui.login.LoginActivity;
import com.starter.template.util.common.CustomAnimationUtils;
import com.starter.template.util.common.UiUtils;


public class SplashActivity
        extends BaseActivity implements
        SplashContract.SplashView {

    private SplashContract.SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenterImpl(this);
        initViews();
    }

    private void initViews() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadContent();
    }

    @Override
    public void launchLoginScreenWithDelay(int delay) {
        launchWithDelay(delay, LoginActivity.class);
    }

    @Override
    public void launchHomeScreenWithDelay(int delay) {
        launchWithDelay(delay, HomeActivity.class);
    }

    @Override
    public void showForceUpdateDialog() {
        // Todo Use on implementing Force update.
        // Call presenter.updateAppRequested(); on user confirmation.
    }

    @Override
    public void launchPlayStoreToPackage() {
        UiUtils.openAppInStore(this);
    }

    private void launchWithDelay(int splashDelay, final Class activityClass) {
        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivityScreen(
                        activityClass,
                        CustomAnimationUtils.getFadeInStartBundle(SplashActivity.this));
                finish();
            }
        }, splashDelay);
    }

}
