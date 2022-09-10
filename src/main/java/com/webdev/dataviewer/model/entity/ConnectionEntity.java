package com.webdev.dataviewer.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import com.webdev.dataviewer.model.connection.DBConnectionDetails;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity(name = "connection")
@Data
@TypeDef(name = "jsonb", typeClass = JsonType.class)
public class ConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "type")
    private String type;

    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private Map<String, Object> details;

}
