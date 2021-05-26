package com.example.baseapplication.common.error;

public abstract class ErrorModel extends RuntimeException {
    private String message;
    private Throwable throwable;
}
