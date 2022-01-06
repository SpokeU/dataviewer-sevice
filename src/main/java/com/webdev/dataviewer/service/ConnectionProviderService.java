package com.webdev.dataviewer.service;

import com.webdev.dataviewer.Connection;
import com.webdev.dataviewer.ConnectionProvider;
import com.webdev.dataviewer.model.api.ConnectionParameter;
import com.webdev.dataviewer.model.connection.ConnectionDetails;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This cl;ass knows nothing about DB and saved connections.
 * Its Domain ConnectionDetails, Type, and Map<String, Object>
 * The purpose is to work on domain models above
 */
@Service
public class ConnectionProviderService {

    private List<ConnectionProvider> connectionProviders;

    public ConnectionProviderService(List<ConnectionProvider> connectionProviders) {
        this.connectionProviders = connectionProviders;
    }

    public List<ConnectionProvider> getAll() {
        return connectionProviders;
    }

    /**
     * Returns all provider names registered in system
     */
    public List<String> getAllProviderNames() {
        return getAll().stream().map(ConnectionProvider::type).collect(Collectors.toList());
    }

    public List<ConnectionParameter> getConnectionParameters(String type) {
        return FieldUtils.getAllFieldsList(getConnectionDetailsClass(type)).stream()
                .map(f -> new ConnectionParameter(f.getName(), getFieldType(f)))
                .collect(Collectors.toList());
    }

    public Class<? extends ConnectionDetails> getConnectionDetailsClass(String type){
        return getByType(type).connectionDetailsClass();
    }

    public ConnectionProvider getByType(String type) {
        return connectionProviders.stream().filter(v -> v.type().equals(type)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No provider for type: " + type + " found"));
    }

    public Connection getConnection(String type, Map<String, Object> details) {
        return getByType(type).getConnection(details);
    }

    private String getFieldType(Field field){
        return StringUtils.substringAfterLast(field.getType().getTypeName(), ".").toLowerCase();
    }
}
