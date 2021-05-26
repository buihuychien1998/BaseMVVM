package com.example.baseapplication.di.module;

import com.example.baseapplication.repository.remote.AuthDataSource;
import com.example.baseapplication.repository.remote.AuthRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
    @Binds
    @Singleton
    public abstract AuthDataSource provideAuthDataSource(AuthRepository authRepository);
}
