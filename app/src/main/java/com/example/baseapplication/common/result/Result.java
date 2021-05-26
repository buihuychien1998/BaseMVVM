package com.example.baseapplication.common.result;


import com.example.baseapplication.common.error.ErrorModel;

public class Result<T> {

    private T data;
    private ErrorModel error;

    public Result(T data) {
        this.data = data;
    }

    public Result(ErrorModel error) {
        this.error = error;
    }

    public Result(T data, ErrorModel error) {
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorModel getError() {
        return error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }
}
