package com.webdev.dataviewer.api;

import com.webdev.dataviewer.*;
import com.webdev.dataviewer.exception.ResourceNotFoundException;
import com.webdev.dataviewer.model.api.ConnectionApiModel;
import com.webdev.dataviewer.model.api.ConnectionParameter;
import com.webdev.dataviewer.providers.PostgresJDBCTemplateConnectionProvider;
import com.webdev.dataviewer.model.entity.ConnectionEntity;
import com.webdev.dataviewer.model.connection.DBConnectionDetails;
import com.webdev.dataviewer.repository.ConnectionRepository;
import com.webdev.dataviewer.service.ConnectionProviderService;
import com.webdev.dataviewer.service.ConnectionService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("connections")
public class ConnectionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConnectionProviderService connectionProviderService;

    @Autowired
    private ConnectionService connectionService;

    @PostMapping
    public ConnectionApiModel create(@RequestBody ConnectionApiModel connection) {
        return connectionService.save(connection);
    }

    @GetMapping("/{id}")
    public ConnectionApiModel getById(@PathVariable Integer id) {
        return connectionService.get(id);
    }

    @GetMapping
    public List<ConnectionApiModel> getAll() {
        return connectionService.getAll();
    }

    @PutMapping("/{id}")
    public ConnectionApiModel update(@PathVariable Integer id, @RequestBody ConnectionApiModel connection) {
        return connectionService.update(id, connection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
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
    public void testSavedConnection(@PathVariable Integer id) {
        ConnectionApiModel apiModel = connectionService.get(id);
        Connection pgConnection = connectionProviderService.getConnection(apiModel.type(), apiModel.connectionDetails());
        QueryResult queryResult = pgConnection.search("select * from connection", new HashedMap());
        logger.info("Result: {}", queryResult);
    }

    @PostMapping("test")
    public void test(@RequestBody ConnectionEntity connection) {
        ConnectionProvider pgConnectionProvider = connectionProviderService.getByType(PostgresJDBCTemplateConnectionProvider.TYPE);
        Connection pgConnection = pgConnectionProvider.getConnection(new DBConnectionDetails("localhost", 5432, "dataviewer", "dataviewer", "dataviewer"));
        QueryResult queryResult = pgConnection.search("select * from connection", new HashedMap());
        logger.info("Result: {}", queryResult);
    }

}
