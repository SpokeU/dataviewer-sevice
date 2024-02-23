package com.webdev.dataviewer.api.mapper;

import com.webdev.dataviewer.api.model.response.QueryApiResponse;
import com.webdev.dataviewer.entity.QueryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class QueryMapper implements EntityApiMapper<QueryEntity, QueryApiResponse>{
}
