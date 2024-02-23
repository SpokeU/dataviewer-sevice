package com.webdev.dataviewer.api.mapper;

import com.webdev.dataviewer.api.model.ConnectionApiModel;
import com.webdev.dataviewer.entity.ConnectionEntity;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper(componentModel = "spring")
public abstract class ConnectionMapper {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract ConnectionApiModel toApiModel(ConnectionEntity connection);



}
