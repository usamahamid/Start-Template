package com.starter.template.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.starter.template.R;
import com.starter.template.app.base.BaseApplication;
import com.starter.template.util.common.CustomAnimationUtils;
import com.starter.template.util.common.NetworkUtil;
import com.starter.template.util.common.UiUtils;


public abstract class BaseActivity
        extends AppCompatActivity
        implements BaseContract.BaseView {
    protected Toolbar toolbar;
    protected AppCompatTextView toolBarTitle;
    protected View content;


    @Override
    protected void onStart() {
        super.onStart();
        content = findViewById(R.id.content);
    }

    protected void initBasicToolbarWithUpEnabled() {
        initBasicToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void initBasicToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarTitle = (AppCompatTextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolBarTitle(int titleResID) {
        if (toolBarTitle != null) {
            toolBarTitle.setText(titleResID);
        }
    }

    protected void launchActivityScreen(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    protected void launchActivityScreen(Class activityClass, Bundle options) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent, options);
    }

    protected Fragment getFragmentByTag(String deviceListFragmentTag) {
        return getSupportFragmentManager().findFragmentByTag(deviceListFragmentTag);
    }

    protected void initFragmentInContent(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment, tag).commit();
    }

    protected void showFragmentInContent(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    protected void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }


    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showNetworkProgress() {
        // Do Nothing
    }

    @Override
    public void dismissNetworkProgress() {
        // Do Nothing
    }

    @Override
    public void showNetworkUnAvailableAlert() {
        // Do Nothing
    }

    @Override
    public boolean isActive() {
        return !isDestroyed();
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtil.isNetworkAvailable(BaseApplication.getAppContext());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnack(String message) {
        if (content == null) {
            return;
        }
        UiUtils.longSnackBar(content, message);
    }

    @Override
    public void finishAndGoBack() {
        CustomAnimationUtils.finishWithSlideToRight(this);
    }

    @Override
    public void showSnack(int strResId) {
        if (content == null) {
            return;
        }
        try {
            String message = getResources().getString(strResId);
            UiUtils.longSnackBar(content, message);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
