package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.DataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.utils.ConverterUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class AppModule {
    Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return app.getSharedPreferences(Constants.PREFS_NAME, 0);
    }

    @Singleton
    @Provides
    RequestParams providesRequestParams() {

        return new RequestParams();
    }

    @Singleton
    @Provides
    ConverterUtils providesConverterUtils() {
        return new ConverterUtils();
    }

    @Singleton
    @Provides
    IDataInteractor provideDataInteractor(Retrofit retrofit, ConverterUtils converter) {
        return DataInteractor.getInstance(retrofit, converter);
    }
}
