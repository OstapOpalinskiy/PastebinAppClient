package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import com.opalinskiy.ostap.pastebin.interactor.DataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.utils.ConverterUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DataModule {
    retrofit2.Retrofit retrofit;
    ConverterUtils converter;

    public DataModule(Retrofit retrofit, ConverterUtils converter) {
        this.retrofit = retrofit;
        this.converter = converter;
    }

    @Singleton
    @Provides
    IDataInteractor provideDataInteractor(){
        return DataInteractor.getInstance(retrofit, converter);
    }
}
