package com.example.baseapplication.common.error;

import androidx.annotation.Nullable;

public abstract class ErrorModel extends RuntimeException {
    private String message;
    private Throwable throwable;

    public ErrorModel(String message, Throwable throwable){
        this.message = message;
        this.throwable = throwable;
    }

    public ErrorModel(Throwable throwable){
        this.throwable = throwable;
    }

    public ErrorModel(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
