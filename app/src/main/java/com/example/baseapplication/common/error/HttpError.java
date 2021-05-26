package com.example.baseapplication.common.error;

public class HttpError extends ErrorModel {

    public HttpError(String message, Throwable throwable) {
        super(message, throwable);
    }

    public HttpError(Throwable throwable) {
        super(throwable);
    }

    public HttpError(String message) {
        super(message);
    }
}
