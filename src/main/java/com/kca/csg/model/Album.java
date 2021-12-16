package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kca.csg.model.audit.UserDateAudit;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "photos", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) } )
public class Album  extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photo;

    @JsonIgnore
    public User getUser(){ return user; }

    public List<Photo> getPhoto(){ return this.photo == null ? null : new ArrayList<>(this.photo); }

    public void setPhoto(List<Photo> photo){
        if(photo == null){ this.photo = null; } else { this.photo = Collections.unmodifiableList(photo); }
    }


}
