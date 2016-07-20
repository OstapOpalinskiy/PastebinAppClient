package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    SharedPreferences preferences();
    RequestParams params();
    IDataInteractor dataInteractor();
    void inject(Application application);
}