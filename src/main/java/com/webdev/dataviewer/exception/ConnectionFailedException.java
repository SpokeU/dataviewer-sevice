package com.webdev.dataviewer.exception;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException(Throwable e) {
        super(e);
    }
}
