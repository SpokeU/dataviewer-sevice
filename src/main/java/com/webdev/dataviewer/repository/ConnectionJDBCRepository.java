package com.webdev.dataviewer.repository;

import com.webdev.dataviewer.model.api.ConnectionApiModel;
import com.webdev.dataviewer.model.connection.DBConnectionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionJDBCRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void create(ConnectionApiModel connection) {

    }

    public List<ConnectionApiModel> getAll() {
        return Collections.emptyList();
    }

    public ConnectionApiModel get(Integer id) {
        jdbc.query("SELECT * FROM connection_parameters cp WHERE cp.connection_id = 2;", BeanPropertyRowMapper.newInstance(DBConnectionDetails.class));


        jdbc.query("SELECT * FROM connection c INNER JOIN connection_parameters cp ON c.id = cp.connection_id;",
                new ResultSetExtractor<ConnectionApiModel>() {
                    @Override
                    public ConnectionApiModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<ConnectionApiModel> connections = new ArrayList<>();

                        while (rs.next()) {

                        }
                        return null;
                    }
                });

        return null;
    }

}
