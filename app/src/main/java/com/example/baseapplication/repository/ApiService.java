package com.example.baseapplication.repository;

import com.example.baseapplication.common.result.Result;
import com.example.baseapplication.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/api/notices/messages")
    Observable<User> login(@Query("username") String username, @Query("password") String password);

    @GET("users/list")
    Observable<List<User>> getUserList();
}
