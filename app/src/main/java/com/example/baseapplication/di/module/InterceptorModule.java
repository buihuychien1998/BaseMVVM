package com.example.baseapplication.di.module;

import com.example.baseapplication.repository.remote.config.CustomInterceptor;
import com.example.baseapplication.di.annotation.NetworkInterceptor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;

@Module
@InstallIn(SingletonComponent.class)
public abstract class InterceptorModule {
    /**
     * Handle network interceptor
     * <p>
     * ネットワークインターセプターを処理する
     */
    @Binds
    @Singleton
    @NetworkInterceptor
    public abstract Interceptor provideNetworkInterceptor(CustomInterceptor interceptor);

}
