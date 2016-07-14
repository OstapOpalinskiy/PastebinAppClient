package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.global.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {
    Context context;

    public PrefsModule(Context context) {
        this.context = context;
    }
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(){
        return context.getSharedPreferences(Constants.PREFS_NAME, 0);
    }
}
