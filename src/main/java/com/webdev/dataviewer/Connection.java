package com.webdev.dataviewer;

import java.util.Map;

/**
 * Wrapper class for any Connection specific object like - java.sql.Connection etc.
 * @param <T>
 */
public abstract class Connection<T> {

    protected T connection;

    public Connection(T connection) {
        this.connection = connection;
    }

    public abstract QueryResult<?> search(String query, Map<String, String> parameters);

    public abstract boolean testConnection();

}
