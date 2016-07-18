package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.LoginPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.screens.login_screen.view.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {PrefsModule.class, DataModule.class, ParamsModule.class, MainPresenterModule.class, LoginPresenterModule.class})
public interface LoginComponent {
        void inject(LoginFragment loginFragment);
}
