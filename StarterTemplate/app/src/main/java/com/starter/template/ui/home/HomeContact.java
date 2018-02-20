package com.starter.template.ui.home;

import com.starter.template.ui.base.BaseContract;

class HomeContact {
    interface HomeView extends BaseContract.BaseView {
        void finishAndGoToLoginScreen();
    }

    interface HomePresenter extends BaseContract.BasePresenter {
        void loadContent();

        void logoutRequested();
    }
}
