package com.webdev.dataviewer.model.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fields required to get a connection
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBConnectionDetails extends ConnectionDetails {

    private String host;
    private String port;
    private String database;

    private String username;
    private String password;

}
