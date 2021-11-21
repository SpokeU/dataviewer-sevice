package com.webdev.dataviewer.service;

import com.webdev.dataviewer.Connection;
import com.webdev.dataviewer.ConnectionProvider;
import com.webdev.dataviewer.model.connection.ConnectionDetails;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConnectionProviderService {

    private List<ConnectionProvider> connectionProviders;

    public ConnectionProviderService(List<ConnectionProvider> connectionProviders) {
        this.connectionProviders = connectionProviders;
    }

    /**
     * Returns all provider names registered in system
     */
    public List<String> getAllProviderNames() {
        return findAll().stream().map(ConnectionProvider::type).collect(Collectors.toList());
    }

    public List<String> getConnectionParameters(String type) {
        return FieldUtils.getAllFieldsList(findByType(type).connectionDetailsClass()).stream().map(Field::getName).collect(Collectors.toList());
    }

    public List<ConnectionProvider> findAll() {
        return connectionProviders;
    }

    public ConnectionProvider findByType(String type) {
        return connectionProviders.stream().filter(v -> v.type().equals(type)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No provider for type: " + type + " found"));
    }

    public Connection getConnection(String type, Map<String, Object> details) {
        return findByType(type).getConnection(details);
    }
}
