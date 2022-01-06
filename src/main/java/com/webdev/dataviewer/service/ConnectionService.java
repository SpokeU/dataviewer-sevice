package com.webdev.dataviewer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.model.api.ConnectionApiModel;
import com.webdev.dataviewer.model.connection.ConnectionDetails;
import com.webdev.dataviewer.model.entity.ConnectionEntity;
import com.webdev.dataviewer.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConnectionService {


    private final ConnectionRepository connectionRepository;

    private final ConnectionProviderService connectionProviderService;

    private ObjectMapper mapper = new ObjectMapper();


    public ConnectionApiModel get(Integer id) {
        return toApiModel(connectionRepository.findById(id).get());
    }

    public List<ConnectionApiModel> getAll() {
        return connectionRepository.findAll().stream().map(this::toApiModel).toList();
    }

    public ConnectionApiModel save(ConnectionApiModel connection) {
        ConnectionEntity connectionEntity = toEntity(connection);
        ConnectionEntity savedEntity = connectionRepository.save(connectionEntity);
        return toApiModel(savedEntity);
    }

    public ConnectionApiModel update(Integer id, ConnectionApiModel connection) {
        return connectionRepository.findById(id).map(entity -> {
            return save(connection.withId(id));
        }).orElseThrow(() -> new ResourceNotFoundException("connection", id));
    }

    public void delete(Integer id) {
        connectionRepository.findById(id).ifPresentOrElse(entity -> {
            connectionRepository.deleteById(id);
        }, () -> {
            throw new ResourceNotFoundException("connection", id);
        });
    }

    private ConnectionApiModel toApiModel(ConnectionEntity entity) {
        Map<String, String> entityConnectionDetails = entity.getConnectionDetails();

        ConnectionDetails connectionDetails = toConnectionDetailsClass(entity.getType(), entityConnectionDetails);
        Map<String, Object> connectionDetailsMap = mapper.convertValue(connectionDetails, Map.class);

        return new ConnectionApiModel(entity.getId(), entity.getName(), entity.getType(), connectionDetailsMap);
    }

    private ConnectionEntity toEntity(ConnectionApiModel apiModel) {
        //Act as validation when trying to convert to Details model if there are Unrecognized field it will throw error
        toConnectionDetailsClass(apiModel.type(), apiModel.connectionDetails());
        ConnectionEntity connectionEntity = mapper.convertValue(apiModel, ConnectionEntity.class);
        return connectionEntity;
    }

    //TODO handle exception of Unrecognized field to return proper message for API
    private ConnectionDetails toConnectionDetailsClass(String connectionType, Map<String, ? extends Object> connectionDetailsMap) {
        Class<? extends ConnectionDetails> connectionDetailsClass = connectionProviderService.getConnectionDetailsClass(connectionType);
        ConnectionDetails connectionDetails = mapper.convertValue(connectionDetailsMap, connectionDetailsClass);
        return connectionDetails;
    }

}
