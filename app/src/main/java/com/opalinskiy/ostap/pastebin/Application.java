package com.opalinskiy.ostap.pastebin;


import android.content.Context;

import com.opalinskiy.ostap.pastebin.interactor.dagger.components.AppComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerAppComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;


public class Application extends android.app.Application {
    private static Context instance;
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return component;
    }

    public static Context getContext() {
        return instance;
    }
}