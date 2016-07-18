package com.opalinskiy.ostap.pastebin.screens.login_screen.presenter;


import com.opalinskiy.ostap.pastebin.screens.login_screen.ILoginScreen;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;

import javax.inject.Inject;


public class LoginPresenter implements ILoginScreen.IPresenter {
    IMainScreen.IPresenter mainPresenter;

   @Inject
    public LoginPresenter(IMainScreen.IPresenter mainPresenter) {

        this.mainPresenter = mainPresenter;
    }

    @Override
    public void onLogin(String login, String password) {

        mainPresenter.onLogin(login, password);
    }
}
