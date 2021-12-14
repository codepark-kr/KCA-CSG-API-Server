package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kca.csg.model.audit.UserDateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "tags")
public class Tag extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "twins_tag", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "twins_id", referencedColumnName = "id"))
    private List<Twins> twins;

    public Tag(String name){
        super();
        this.name = name;
    }

    public List<Twins> getTwins(){ return twins == null ? null : new ArrayList<>(twins); }

    public void setTwins(List<Twins> twins){ if(twins == null){ this.twins = null; } else { this.twins = Collections.unmodifiableList(twins); } }
}
