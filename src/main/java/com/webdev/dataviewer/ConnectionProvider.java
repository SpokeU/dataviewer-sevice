package com.webdev.dataviewer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webdev.dataviewer.model.connection.ConnectionDetails;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface ConnectionProvider<D extends ConnectionDetails, C extends Connection> {

    String type();

    Class<D> connectionDetailsClass();

    default C getConnection(Map<String, Object> parameters) {
        return getConnection(new ObjectMapper().convertValue(parameters, connectionDetailsClass()));
    }

    C getConnection(D details);

}
