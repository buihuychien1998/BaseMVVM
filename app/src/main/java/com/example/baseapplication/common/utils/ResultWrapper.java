package com.example.baseapplication.common.utils;

import com.example.baseapplication.common.result.Result;

/**
 * Every method that manupulate with data should be wrapped on Result class.
 * It can hold Data or ErrorModel
 *
 * データを操作するすべてのメソッドは、Resultクラスでラップする必要があります。
 * DataまたはErrorModelを保持できます
 */
public interface ResultWrapper {

    <T> Result<T> getData(T result);

    <T> Result<T> getData(T result, Throwable throwable);

    Result getError(Throwable throwable);
}