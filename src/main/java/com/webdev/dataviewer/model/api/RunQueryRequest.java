package com.webdev.dataviewer.model.api;

import java.util.Map;

public record RunQueryRequest(Integer connectionId,
                              Integer queryId,
                              String queryString,
                              Map<String, Object> queryParams) {

}
