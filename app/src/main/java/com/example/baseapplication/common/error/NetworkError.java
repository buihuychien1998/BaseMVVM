package com.example.baseapplication.common.error;

public class NetworkError extends ErrorModel {
    private static final String defaultMessage = "Connection error";
    public NetworkError(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NetworkError(Throwable throwable) {
        super(throwable);
    }

    public NetworkError(String message) {
        super(message);
    }
    public NetworkError() {
        super(defaultMessage);
    }
}
