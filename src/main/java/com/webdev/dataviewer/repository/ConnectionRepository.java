package com.webdev.dataviewer.repository;

import com.webdev.dataviewer.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "connections", path = "connections")
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Integer> {
}
