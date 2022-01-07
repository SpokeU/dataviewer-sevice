package com.webdev.dataviewer.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "query")
@Data
public class QueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "connection_id")
    private Integer connectionId;

    @Column(name = "query_string")
    private String queryString;

}
