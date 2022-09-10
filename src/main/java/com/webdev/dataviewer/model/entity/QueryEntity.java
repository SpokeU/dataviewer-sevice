package com.webdev.dataviewer.model.entity;

import com.webdev.dataviewer.Connection;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "queries")
@Data
public class QueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "connection_id")
    private Integer connectionId;

    /*@ManyToOne
    @JoinColumn(name = "connection_id")
    private Connection connectionId;*/

    @Column(name = "query_string")
    private String queryString;

}
