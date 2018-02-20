package com.starter.template.ui.login;

import com.starter.template.app.AppPreferences;

class LoginPresenterImpl implements LoginContact.LoginPresenter {

    private final LoginContact.LoginView view;
    private final AppPreferences pref;

    LoginPresenterImpl(LoginContact.LoginView view) {
        this.view = view;
        pref = AppPreferences.getPref(view.getViewContext());
    }

    @Override
    public void loadContent() {

    }

    @Override
    public void loginRequested(String userName, String password) {
        pref.setUserName(userName);
        pref.setPassword(password);
        view.navigateToHomePageAndFinish();
    }
}
