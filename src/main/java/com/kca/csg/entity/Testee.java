package com.kca.csg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TESTEE")
public class Testee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String testTitle;
    private String testDescription;
    private Timestamp testDate;
}
