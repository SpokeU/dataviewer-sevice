package com.webdev.dataviewer.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CollectionType;
import org.hibernate.type.MapType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity(name = "connection")
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
    private Map<String, Object> connectionDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setConnectionDetails(Map<String, Object> connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

    public Map<String, Object> getConnectionDetails() {
        return new HashMap<>(connectionDetails);
    }

}
