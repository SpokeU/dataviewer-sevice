package com.webdev.dataviewer.repository;

import com.webdev.dataviewer.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Integer> {
}
