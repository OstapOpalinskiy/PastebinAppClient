package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.utils.ConverterUtils;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Retrofit retrofit();
    SharedPreferences preferences();
    RequestParams params();
    IDataInteractor dataInteractor();
    ConverterUtils converter();
    void inject(Application application);
}