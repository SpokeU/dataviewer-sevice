package com.webdev.dataviewer.api.mapper;

public interface EntityApiMapper<E, A> {

    A toApiModel(E entity);

    E toEntity(A entity);

}
