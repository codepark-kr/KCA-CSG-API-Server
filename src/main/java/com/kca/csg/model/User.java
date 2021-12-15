package com.kca.csg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kca.csg.model.audit.DateAudit;
import com.kca.csg.model.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
    @UniqueConstraint(columnNames = { "email" })})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank@Column(name = "first_name")
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Column(name = "username")
    @Size(max = 14)
    private String username;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "email")
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "contact")
    private String contact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Twins> twins;

    public User(String firstName, String lastName, String username, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public List<Role> getRoles(){ return roles == null ? null : new ArrayList<>(roles); }
    public void setRoles(List<Role> roles){ if(roles == null){ this.roles = null; } else { this.roles = Collections.unmodifiableList(roles); } }

    public List<Twins> getTwins(){ return twins == null ? null : new ArrayList<>(twins); }
    public void setTwins(List<Twins> twins){ if(twins == null){ this.twins = null; } else this.twins = Collections.unmodifiableList(twins); }
}
