package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;


import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.IProfileScreen;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.presenter.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {
    IProfileScreen.IProfileView view;

    public ProfileModule(IProfileScreen.IProfileView view) {
        this.view = view;
    }

    @Provides
    IProfileScreen.IPresenter provideProfileModule(IMainScreen.IPresenter mainPresenter, IDataInteractor model
            , RequestParams parameters, SharedPreferences prefs) {
        return new ProfilePresenter(mainPresenter, view, model, parameters, prefs);
    }
}