package com.webdev.dataviewer.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        this(String.format("'%s' with id: '%d' is not found", resourceName, id));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
