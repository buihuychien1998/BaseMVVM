package com.example.baseapplication.di.module;

import com.example.baseapplication.BuildConfig;
import com.example.baseapplication.di.annotation.NetworkInterceptor;
import com.example.baseapplication.di.annotation.OkHttpClientNetwork;
import com.example.baseapplication.repository.ApiService;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    /**
     * @Provides annotation is used for provide dependency of third party library
     * @Binds annotation is used for binding an interface with implementation
     */
    private static String BASE_URL = "https://api.github.com/";

    @Provides
    @Singleton
    public RxJava3CallAdapterFactory provideRxCallAdapter() {
        return RxJava3CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    @OkHttpClientNetwork
    public OkHttpClient provideOkHttpClient(
        HttpLoggingInterceptor httpLoggingInterceptor,
        @NetworkInterceptor Interceptor interceptor
    ) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true);

        return builder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(
        @OkHttpClientNetwork OkHttpClient okHttpClient,
        RxJava3CallAdapterFactory rxJava3CallAdapterFactory,
        GsonConverterFactory gsonConverterFactory
    ) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .build()
            .create(ApiService.class);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        Level level = BuildConfig.DEBUG ? Level.BODY : Level.NONE;
        httpLoggingInterceptor.setLevel(level);
        return httpLoggingInterceptor;
    }


    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

}