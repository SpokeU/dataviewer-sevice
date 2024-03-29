package com.webdev.dataviewer.repository;

import com.webdev.dataviewer.entity.QueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<QueryEntity, Integer> {
}
