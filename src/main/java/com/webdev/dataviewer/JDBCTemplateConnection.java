package com.webdev.dataviewer;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class JDBCTemplateConnection extends Connection<NamedParameterJdbcTemplate> {

    public JDBCTemplateConnection(NamedParameterJdbcTemplate connection) {
        super(connection);
    }

    @Override
    public QueryResult<?> search(String query, Map<String, String> parameters) {
        List<Map<String, Object>> result = connection.queryForList(query, parameters);
        return new QueryResult(result);
    }

    @Override
    public boolean testConnection() {
        return false;
    }
}
