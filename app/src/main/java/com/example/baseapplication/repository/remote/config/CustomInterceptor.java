package com.example.baseapplication.repository.remote.config;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class CustomInterceptor implements Interceptor {

    @Inject
    public CustomInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {
    /*
    chain.request() returns original request that you can work with(modify, rewrite)
    */
        Request request = chain.request();

        // Here you can rewrite the request

        /*
    chain.proceed(request) is the call which will initiate the HTTP work. This call invokes the request and returns the response as per the request.
        */
        Response response = chain.proceed(request);

        //Here you can rewrite/modify the response

        return response;
    }
}
