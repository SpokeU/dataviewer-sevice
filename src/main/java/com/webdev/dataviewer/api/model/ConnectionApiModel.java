package com.webdev.dataviewer.api.model;

import java.util.Map;

public record ConnectionApiModel(
        Integer id,
        String name,
        String type,
        Map<String, Object> details
) {
    public ConnectionApiModel withId(Integer id) {
        return new ConnectionApiModel(id, name(), type(), details());
    }
}
