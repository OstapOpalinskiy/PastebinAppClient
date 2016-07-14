package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import com.opalinskiy.ostap.pastebin.interactor.RequestParams;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParamsModule  {
    @Singleton
    @Provides
    RequestParams providesRequestParams(){
        return new RequestParams();
    }
}
