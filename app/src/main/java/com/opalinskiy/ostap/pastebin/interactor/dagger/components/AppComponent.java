package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;
import com.opalinskiy.ostap.pastebin.screens.main_screen.presenter.MainScreenPresenter;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.presenter.ProfilePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PrefsModule.class, DataModule.class, ParamsModule.class})
public interface AppComponent {
    void inject(MainScreenPresenter mainScreenPresenter);
    void inject(ProfilePresenter profilePresenter);
}