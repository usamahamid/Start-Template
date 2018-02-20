package com.starter.template.ui.login;

import android.os.Bundle;
import android.view.View;

import com.starter.template.R;
import com.starter.template.ui.base.BaseActivity;
import com.starter.template.ui.home.HomeActivity;
import com.starter.template.util.common.CustomAnimationUtils;

public class LoginActivity
        extends BaseActivity implements
        LoginContact.LoginView, View.OnClickListener {
    private LoginContact.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_wrapper);
        presenter = new LoginPresenterImpl(this);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void navigateToHomePageAndFinish() {
        content.postDelayed(new Runnable() {
            @Override
            public void run() {
                launchScreenAndFinish(HomeActivity.class);
            }
        }, 300);
    }

    private void launchScreenAndFinish(Class activityClass) {
        Bundle options =
                CustomAnimationUtils.getSlideInFromBottomStartBundle(this);
        launchActivityScreen(activityClass, options);
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
            case android.support.design.R.id.snackbar_action:
                presenter.loginRequested("Dummy User", "Dummy Password");
                break;
        }
    }
}
