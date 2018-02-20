package com.starter.template.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.starter.template.R;
import com.starter.template.app.base.BaseApplication;
import com.starter.template.util.common.NetworkUtil;
import com.starter.template.util.common.UiUtils;


public abstract class BaseFragment
        extends Fragment
        implements BaseContract.BaseView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    protected void launchActivityScreen(Class activityClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent, bundle);
    }

    public boolean onBackPressed() {
        //Do nothing.
        return false;
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void showToast(String msg) {
        if (!isActive()) {
            return;
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnack(int strResId) {
        View content = isActive() ? getActivity().findViewById(R.id.content) : null;
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


    @Override
    public void showSnack(String message) {
        View content = isActive() ? getActivity().findViewById(R.id.content) : null;
        if (content == null) {
            return;
        }
        UiUtils.longSnackBar(content, message);
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetworkUtil.isNetworkAvailable(BaseApplication.getAppContext());
    }
}
