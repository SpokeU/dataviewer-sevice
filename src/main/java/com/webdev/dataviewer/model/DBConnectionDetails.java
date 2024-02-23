package com.webdev.dataviewer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Fields required to get a connection
 */

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class DBConnectionDetails extends ConnectionDetails {

    private String host;
    private Integer port;
    private String database;

    private String username;
    private String password;

}
