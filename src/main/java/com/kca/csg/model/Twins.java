package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kca.csg.model.audit.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "TWINS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Twins extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "korTitle")
    private String korTitle;

    @Column(name = "korContent")
    private String korContent;

    @Column(name = "engTitle")
    private String engTitle;

    @Column(name = "engContent")
    private String engContent;

}
