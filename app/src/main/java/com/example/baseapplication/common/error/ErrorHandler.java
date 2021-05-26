package com.example.baseapplication.common.error;

public interface ErrorHandler {
    void onUnknownError(ErrorModel errorModel);

    void onTimeout();

    void onNetworkError();

    void onHttpError(ErrorModel errorModel);
}
