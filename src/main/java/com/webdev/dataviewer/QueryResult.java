package com.webdev.dataviewer;

import lombok.ToString;

/**
 * Is a wrapper for query result (ResultSet from JDBC or ... from Mongo driver) result
 */
@ToString
public class QueryResult<T> {

    T result;

    public QueryResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

}
