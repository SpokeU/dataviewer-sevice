package com.webdev.dataviewer;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JDBCTemplateConnection extends Connection<NamedParameterJdbcTemplate> {

    public JDBCTemplateConnection(NamedParameterJdbcTemplate connection) {
        super(connection);
    }

    @Override
    public QueryResult<?> search(String query, Map<String, Object> parameters) {
        //\$\{([a-zA-Z0-9_]+)\}
        String formattedQuery = formatQueryParameters(query);
        Map<String, Object> queryParameters = convertParameterTypes(parameters);
        List<Map<String, Object>> result = connection.queryForList(formattedQuery, queryParameters);

        return new QueryResult(result);
    }

    @Override
    public void testConnection() {
        search("SELECT 1", null);
    }

    private String formatQueryParameters(String originalSql) {
        StringBuilder resultSql = new StringBuilder(originalSql);
        Matcher matcher = Pattern.compile("[$][{](\\w+)}").matcher(originalSql);

        while (matcher.find()) {
            String key = matcher.group(1);

            String paramName = "${" + key + "}";
            int index = resultSql.indexOf(paramName);
            if (index != -1) {
                resultSql.replace(index, index + paramName.length(), ":" + key);
            }
        }
        return resultSql.toString();
    }

    private Map<String, Object> convertParameterTypes(Map<String, Object> parameters) {
        if(parameters == null){
            return new HashMap<>();
        }

        Map<String, Object> result = new HashMap<>(parameters.size());

        parameters.forEach((k, v) -> {
            result.put(k, transformValue(v));
        });

        return result;
    }

    private Object transformValue(Object value) {
        if (value != null && ClassUtils.isAssignable(value.getClass(), String.class)) {
            return transformString(value.toString());
        }

        return value;
    }

    private Object transformString(String value) {
        if (StringUtils.isNumeric(value)) {
            return Integer.valueOf(value);
        }
        return value;
    }
}
