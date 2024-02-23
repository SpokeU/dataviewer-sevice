package com.webdev.dataviewer.api.model.request;

import java.util.Map;

public record RunQueryRequest(Integer connectionId,
                              Integer queryId,
                              String queryString,
                              Map<String, Object> queryParams) {

}
