package com.kca.csg.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kca.csg.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails{
    private Long id;
    private String firstName;
    private String lastName;
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String firstName, String lastName, String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;

        if(authorities == null){ this.authorities = null; } else { this.authorities = new ArrayList<>(authorities); }
    }
        public static UserPrincipal create(User user){
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

            return new UserPrincipal(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getEmail(), user.getPassword(), authorities);
        }
}

