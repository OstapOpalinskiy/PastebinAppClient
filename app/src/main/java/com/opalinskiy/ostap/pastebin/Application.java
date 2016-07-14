package com.opalinskiy.ostap.pastebin;


import android.content.Context;


import com.opalinskiy.ostap.pastebin.interactor.dagger.components.AppComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerAppComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;

import com.opalinskiy.ostap.pastebin.interactor.ConnectProvider;
import com.opalinskiy.ostap.pastebin.utils.ConverterUtils;


public class Application extends android.app.Application {
    private static Context instance;
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder()
                .dataModule(new DataModule(ConnectProvider.getInstance().getRetrofit(), new ConverterUtils()))
                .prefsModule(new PrefsModule(Application.getContext()))
                .paramsModule(new ParamsModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static Context getContext() {
        return instance;
    }
}
