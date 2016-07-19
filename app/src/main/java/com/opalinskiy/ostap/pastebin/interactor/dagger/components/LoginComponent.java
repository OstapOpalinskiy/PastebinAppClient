package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.PerFragment;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.LoginPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.screens.login_screen.view.LoginFragment;

import dagger.Component;


@PerFragment
@Component(modules = {MainPresenterModule.class, LoginPresenterModule.class}, dependencies = AppComponent.class)
public interface LoginComponent {
        void inject(LoginFragment loginFragment);
}
