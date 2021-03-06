package com.opalinskiy.ostap.pastebin.interactor;

import com.opalinskiy.ostap.pastebin.global.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ConnectProvider {
    private static ConnectProvider instance;
    private final Retrofit retrofit;

    private ConnectProvider() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static ConnectProvider getInstance() {
        if (instance == null) {
            instance = new ConnectProvider();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
