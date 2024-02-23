package com.webdev.dataviewer.api.model.response;

public record QueryApiResponse(
        Integer id,
        String name,
        Integer connectionId,
        String queryString) {
}
