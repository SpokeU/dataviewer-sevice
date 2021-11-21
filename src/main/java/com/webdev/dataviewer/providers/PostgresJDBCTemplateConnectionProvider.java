package com.webdev.dataviewer.providers;

import com.webdev.dataviewer.ConnectionProvider;
import com.webdev.dataviewer.JDBCTemplateConnection;
import com.webdev.dataviewer.model.connection.DBConnectionDetails;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgresJDBCTemplateConnectionProvider implements ConnectionProvider<DBConnectionDetails, JDBCTemplateConnection> {

    public static final String TYPE = "POSTGRES";

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public Class<DBConnectionDetails> connectionDetailsClass() {
        return DBConnectionDetails.class;
    }

    @Override
    public JDBCTemplateConnection getConnection(DBConnectionDetails connectionDetails) {
        BasicDataSource dataSource = new BasicDataSource();

        String url = "jdbc:postgresql://" +
                connectionDetails.getHost() + ":" +
                connectionDetails.getPort() + "/" +
                connectionDetails.getDatabase();


        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(connectionDetails.getUsername());
        dataSource.setPassword(connectionDetails.getPassword());

        return new JDBCTemplateConnection(new NamedParameterJdbcTemplate(dataSource));
    }

}
