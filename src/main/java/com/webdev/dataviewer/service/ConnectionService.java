package com.webdev.dataviewer.service;

import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.model.api.ConnectionApiModel;
import com.webdev.dataviewer.model.entity.ConnectionEntity;
import com.webdev.dataviewer.repository.ConnectionRepository;
import com.webdev.dataviewer.util.ConnectionModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    private final ConnectionModelMapper connectionModelMapper;


    public ConnectionApiModel get(Integer id) {
        return connectionModelMapper.toApiModel(connectionRepository.findById(id).get());
    }

    public List<ConnectionApiModel> getAll() {
        return connectionRepository.findAll().stream().map(connectionModelMapper::toApiModel).toList();
    }

    public ConnectionApiModel save(ConnectionApiModel connection) {
        ConnectionEntity connectionEntity = connectionModelMapper.toEntity(connection);
        ConnectionEntity savedEntity = connectionRepository.save(connectionEntity);
        return connectionModelMapper.toApiModel(savedEntity);
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

}
