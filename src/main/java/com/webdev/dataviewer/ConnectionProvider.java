package com.webdev.dataviewer;

import com.webdev.dataviewer.model.ConnectionDetails;


public interface ConnectionProvider<D extends ConnectionDetails, C extends Connection> {

    String type();

    Class<D> connectionDetailsClass();

    C getConnection(D details);

}
