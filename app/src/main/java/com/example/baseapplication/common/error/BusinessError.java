package com.example.baseapplication.common.error;

public class BusinessError extends ErrorModel {

    public BusinessError(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessError(Throwable throwable) {
        super(throwable);
    }

    public BusinessError(String message) {
        super(message);
    }
}
