package com.example.baseapplication.repository.remote;

import com.example.baseapplication.common.result.Result;
import com.example.baseapplication.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface AuthDataSource {
    Observable<User> login(String username, String password);

    Observable<List<User>> getUserList();
}
