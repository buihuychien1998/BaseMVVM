package com.example.baseapplication.data.remote;

import com.example.baseapplication.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface AuthDataSource {
    Observable<User> login(String username, String password);

    Observable<List<User>> getUserList();
}
