package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.IMyPastesScreen;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.presenter.MyPastesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MyPastesModule {
    IMyPastesScreen.IView view;
    int myOrTrending;

    public MyPastesModule(IMyPastesScreen.IView view, int myOrTrending) {
        this.view = view;
        this.myOrTrending = myOrTrending;
    }

    @Provides
    IMyPastesScreen.IPresenter provideMyPastesPresenter(IDataInteractor model, RequestParams
            parameters, SharedPreferences prefs){
        return new MyPastesPresenter(view, myOrTrending, model, parameters, prefs);
    }

}
