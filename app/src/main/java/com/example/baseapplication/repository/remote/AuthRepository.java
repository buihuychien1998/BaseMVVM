package com.example.baseapplication.repository.remote;

import com.example.baseapplication.common.result.Result;
import com.example.baseapplication.model.User;
import com.example.baseapplication.repository.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class AuthRepository implements AuthDataSource {
    private final ApiService apiService;

    @Inject
    public AuthRepository(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public Observable<User> login(String username, String password) {
        return apiService.login(username, password);
    }

    @Override
    public Observable<List<User>> getUserList() {
        return apiService.getUserList();
    }
}
