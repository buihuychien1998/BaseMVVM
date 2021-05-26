package com.example.baseapplication.common.error;

public class TimeOutError extends ErrorModel {
    private static final String defaultMessage = "Connection time out";
    public TimeOutError(String message, Throwable throwable) {
        super(message, throwable);
    }

    public TimeOutError(Throwable throwable) {
        super(throwable);
    }

    public TimeOutError(String message) {
        super(message);
    }
    public TimeOutError() {
        super(defaultMessage);
    }
}
