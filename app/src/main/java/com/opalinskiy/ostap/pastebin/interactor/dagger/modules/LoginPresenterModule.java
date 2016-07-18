package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import com.opalinskiy.ostap.pastebin.screens.login_screen.presenter.LoginPresenter;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginPresenterModule {

    @Provides
    LoginPresenter provideLoginPresenterModule(IMainScreen.IPresenter presenter){
        return new LoginPresenter(presenter);
    }
}
