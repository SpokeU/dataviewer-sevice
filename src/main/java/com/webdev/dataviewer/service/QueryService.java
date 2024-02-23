package com.webdev.dataviewer.service;

import com.webdev.dataviewer.Connection;
import com.webdev.dataviewer.QueryResult;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.entity.QueryEntity;
import com.webdev.dataviewer.repository.QueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final QueryRepository queryRepository;

    private final ConnectionService connectionService;

    public QueryResult<?> runQuery(int connectionId, int queryId, Map<String, String> queryParams) {
        Connection connection = connectionService.getConnection(connectionId);
        QueryEntity query = queryRepository.findById(queryId).orElseThrow(() -> new ResourceNotFoundException("query", queryId));

        return connection.search(query.getQueryString(), queryParams);

    }

    public QueryResult<?> runQuery(int connectionId, String queryString, Map<String, Object> queryParams) {
        Connection connection = connectionService.getConnection(connectionId);
        return connection.search(queryString, queryParams);

    }

}
