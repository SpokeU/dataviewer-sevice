package com.webdev.dataviewer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webdev.dataviewer.ConnectionProvider;
import com.webdev.dataviewer.model.api.ConnectionApiModel;
import com.webdev.dataviewer.model.connection.ConnectionDetails;
import com.webdev.dataviewer.model.entity.ConnectionEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConnectionModelMapper {

    private ObjectMapper mapper = new ObjectMapper();

    private final List<ConnectionProvider> connectionProviders;

    private Map<String, Class<? extends ConnectionDetails>> connectionDetailsClassMap;

    public ConnectionModelMapper(List<ConnectionProvider> connectionProviders) {
        this.connectionProviders = connectionProviders;
        this.connectionDetailsClassMap = connectionProviders.stream().collect(Collectors.toMap(ConnectionProvider::type, ConnectionProvider::connectionDetailsClass));
    }

    public ConnectionApiModel toApiModel(ConnectionEntity entity) {
        Map<String, String> entityConnectionDetails = entity.getConnectionDetails();

        ConnectionDetails connectionDetails = toConnectionDetails(entity.getType(), entityConnectionDetails);
        Map<String, Object> connectionDetailsMap = mapper.convertValue(connectionDetails, Map.class);

        return new ConnectionApiModel(entity.getId(), entity.getName(), entity.getType(), connectionDetailsMap);
    }

    public ConnectionEntity toEntity(ConnectionApiModel apiModel) {
        //Act as validation when trying to convert to Details model if there are Unrecognized field it will throw error
        toConnectionDetails(apiModel.type(), apiModel.connectionDetails());
        ConnectionEntity connectionEntity = mapper.convertValue(apiModel, ConnectionEntity.class);
        return connectionEntity;
    }

    //TODO handle exception of Unrecognized field to return proper message for API
    public ConnectionDetails toConnectionDetails(String connectionType, Map<String, ? extends Object> connectionDetailsMap) {
        Class<? extends ConnectionDetails> connectionDetailsClass = connectionDetailsClassMap.get(connectionType);
        ConnectionDetails connectionDetails = mapper.convertValue(connectionDetailsMap, connectionDetailsClass);
        return connectionDetails;
    }

    public ConnectionDetails toConnectionDetails(Class<? extends ConnectionDetails> connectionDetailsClass, Map<String, ? extends Object> connectionDetailsMap) {
        return mapper.convertValue(connectionDetailsMap, connectionDetailsClass);
    }
}
