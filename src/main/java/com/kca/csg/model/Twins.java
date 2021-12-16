package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kca.csg.model.audit.DateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "twins", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "twins_tag", joinColumns = @JoinColumn(name = "twins_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    @JsonIgnore
    public User getUser(){ return user; }
    public void setUser(User user){ this.user = user; }

    public List<Comment> getComments(){ return comments == null ? null : new ArrayList<>(comments); }
    public void setComments(List<Comment> comments){ if(comments == null){ this.comments = null; } else { this.comments = Collections.unmodifiableList(comments); } }

    public List<Tag> getTags(){ return tags == null ? null : new ArrayList<>(tags); }
    public void setTags(List<Tag> tags){ if(tags == null){ this.tags = null; } else { this.tags = Collections.unmodifiableList(tags); } }

}
