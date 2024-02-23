package com.webdev.dataviewer.api.controller;

import com.webdev.dataviewer.api.mapper.ConnectionMapper;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.api.model.ConnectionApiModel;
import com.webdev.dataviewer.api.model.ConnectionParameter;
import com.webdev.dataviewer.api.model.response.ConnectionTestResponse;
import com.webdev.dataviewer.entity.ConnectionEntity;
import com.webdev.dataviewer.repository.ConnectionRepository;
import com.webdev.dataviewer.service.ConnectionProviderService;
import com.webdev.dataviewer.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("connections")
public class ConnectionsController implements CrudController<ConnectionApiModel> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConnectionProviderService connectionProviderService;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionMapper connectionMapper;

    public ConnectionApiModel create(ConnectionApiModel connection) {
        return connectionService.save(connection);
    }

    public ConnectionApiModel findById(Integer id) {
        ConnectionApiModel connection = connectionRepository.findById(id)
                .map(connectionMapper::toApiModel)
                .orElseThrow(() -> new ResourceNotFoundException("connection", id));
        return connection;
    }

    public List<ConnectionApiModel> findAll() {
        return connectionService.getAll();
    }

    public ConnectionApiModel update(Integer id, ConnectionApiModel connection) {
        return connectionService.update(id, connection);
    }

    public void delete(Integer id) {
        connectionService.delete(id);
    }

    @GetMapping("types")
    public List<String> getConnectionTypes() {
        return connectionProviderService.getAllProviderNames();
    }

    @GetMapping("/{type}/parameters")
    public List<ConnectionParameter> getConnectionParameters(@PathVariable String type) {
        return connectionProviderService.getConnectionParameters(type);
    }

    @PostMapping("test/{id}")
    public ConnectionTestResponse testSavedConnection(@PathVariable Integer id) {
        try {
            connectionService.getConnection(id).testConnection();
            return new ConnectionTestResponse(true, "Connection successful");
        } catch (Exception e){
            return new ConnectionTestResponse(false, e.getMessage());
        }

    }

    @PostMapping("test")
    public ConnectionTestResponse test(@RequestBody ConnectionEntity connection) {
        try {
            connectionProviderService
                    .getConnection(connection.getType(), connection.getDetails())
                    .testConnection();
            return new ConnectionTestResponse(true, "Connection successful");
        } catch (Exception e){
            logger.error("Error while testing: ", e);
            return new ConnectionTestResponse(false, e.getMessage());
        }

    }

}
