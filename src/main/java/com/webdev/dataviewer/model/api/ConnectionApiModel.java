package com.webdev.dataviewer.model.api;

import com.webdev.dataviewer.model.connection.ConnectionDetails;

import java.util.Map;

public record ConnectionApiModel(
        Integer id,
        String name,
        String type,
        Map<String, Object> connectionDetails
) {
    public ConnectionApiModel withId(Integer id) {
        return new ConnectionApiModel(id, name(), type(), connectionDetails());
    }
}
