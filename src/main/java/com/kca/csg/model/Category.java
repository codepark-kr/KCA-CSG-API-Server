package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kca.csg.model.audit.UserDateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Twins> twins;

    public Category(String name){
        super();
        this.name = name;
    }

    public List<Twins> getTwins(){ return this.twins == null ? null : new ArrayList<>(this.twins); }
    public void setTwins(List<Twins> twins){ if(twins == null){ this.twins = null; } else { this.twins = Collections.unmodifiableList(twins); }}
}
