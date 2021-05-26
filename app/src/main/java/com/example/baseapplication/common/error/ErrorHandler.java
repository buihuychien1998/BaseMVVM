package com.example.baseapplication.common.error;

public interface ErrorHandler {
    void onUnknownError(String errorMessage);

    void onTimeout();

    void onNetworkError();
}
