package com.webdev.dataviewer.api;

import com.webdev.dataviewer.*;
import com.webdev.dataviewer.providers.PostgresJDBCTemplateConnectionProvider;
import com.webdev.dataviewer.entity.ConnectionEntity;
import com.webdev.dataviewer.model.connection.DBConnectionDetails;
import com.webdev.dataviewer.repository.ConnectionRepository;
import com.webdev.dataviewer.service.ConnectionProviderService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("connection")
public class ConnectionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionProviderService connectionProviderService;

    @GetMapping("types")
    public List<String> getConnectionTypes() {
        return connectionProviderService.getAllProviderNames();
    }

    @GetMapping("/{type}/parameters")
    public List<String> getConnectionParameters(@PathVariable String type){
        return connectionProviderService.getConnectionParameters(type);
    }

    @PostMapping("test/{id}")
    public void testSavedConnection(@PathVariable Integer id) {
        ConnectionEntity byId = connectionRepository.findById(id).get();
        Connection pgConnection = connectionProviderService.getConnection(byId.getType(), byId.getConnectionDetails());
        QueryResult queryResult = pgConnection.search("select * from connection", new HashedMap());
        logger.info("Result: {}", queryResult);
    }

    @PostMapping("test")
    public void test(@RequestBody ConnectionEntity connection) {
        ConnectionProvider pgConnectionProvider = connectionProviderService.findByType(PostgresJDBCTemplateConnectionProvider.TYPE);

        Connection pgConnection = pgConnectionProvider.getConnection(new DBConnectionDetails("localhost", "5432", "dataviewer", "dataviewer", "dataviewer"));

        QueryResult queryResult = pgConnection.search("select * from connection", new HashedMap());
        logger.info("Result: {}", queryResult);
    }

}
