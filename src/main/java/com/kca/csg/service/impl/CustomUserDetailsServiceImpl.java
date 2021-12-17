package com.kca.csg.service.impl;

import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail){
        return UserPrincipal.create(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username of email: %s", usernameOrEmail))));
    }

    @Override
    @Transactional
    public UserDetails loadUserById(Long id){
        return UserPrincipal.create(userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id))));
    }
}
