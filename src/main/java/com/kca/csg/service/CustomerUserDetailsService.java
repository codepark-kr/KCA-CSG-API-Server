package com.kca.csg.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerUserDetailsService {

    UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException;

    UserDetails loadUserById(Long id);
}
