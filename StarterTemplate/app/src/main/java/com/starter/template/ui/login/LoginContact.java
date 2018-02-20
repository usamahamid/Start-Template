package com.starter.template.ui.login;

import com.starter.template.ui.base.BaseContract;

 class LoginContact {
    interface LoginView extends BaseContract.BaseView {
        void navigateToHomePageAndFinish();
    }

    interface LoginPresenter extends BaseContract.BasePresenter {
        void loadContent();

        void loginRequested(String userName, String password);
    }
}
