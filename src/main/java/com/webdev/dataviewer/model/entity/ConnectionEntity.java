package com.webdev.dataviewer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity(name = "connection")
@Data
public class ConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "type")
    private String type;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "connection_parameters", joinColumns = @JoinColumn(name = "connection_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> connectionDetails;

}
