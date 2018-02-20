package com.starter.template.ui.home;

import com.starter.template.app.AppPreferences;

public class HomePresenterImpl implements HomeContact.HomePresenter {

    private final HomeContact.HomeView view;
    private final AppPreferences pref;

    public HomePresenterImpl(HomeContact.HomeView view) {
        this.view = view;
        pref = AppPreferences.getPref(view.getViewContext());
    }

    @Override
    public void loadContent() {

    }

    public void logoutRequested() {
        pref.clearUser();
        view.finishAndGoToLoginScreen();
    }
}
