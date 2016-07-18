package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;
import com.opalinskiy.ostap.pastebin.screens.main_screen.presenter.MainScreenPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {
    IMainScreen.IView view;

    public MainPresenterModule(IMainScreen.IView view) {
        this.view = view;
    }

    @Provides
    IMainScreen.IPresenter provideMainScreenPresenter(IDataInteractor model,
                                                      RequestParams parameters,
                                                      SharedPreferences prefs){
        return new MainScreenPresenter(view, model, parameters, prefs);
    }
}