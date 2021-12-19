package com.kca.csg.controller;

import com.kca.csg.exception.ApiException;
import com.kca.csg.exception.AppException;
import com.kca.csg.model.User;
import com.kca.csg.model.role.Role;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.LoginRequest;
import com.kca.csg.payload.request.SignUpRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.JwtAuthenticationResponse;
import com.kca.csg.repository.RoleRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.kca.csg.util.AppConstants.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){

        if(Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))){
            throw new ApiException(HttpStatus.BAD_REQUEST, EXIST_USERNAME); }
        if(Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))){
            throw new ApiException(HttpStatus.BAD_REQUEST, EXIST_EMAIL); }

        User user = User.builder()
                .firstName(signUpRequest.getFirstName().toLowerCase())
                .lastName(signUpRequest.getLastName().toLowerCase())
                .username(signUpRequest.getUsername().toLowerCase())
                .email(signUpRequest.getEmail().toLowerCase())
                .password(passwordEncoder.encode(signUpRequest.getPassword().toLowerCase()))
                .build();

        log.info("? {}", user);
        List<Role> roles = new ArrayList<>();

        if(userRepository.count() == 0){
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException(ROLE_NOTSET)));
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException(ROLE_NOTSET)));
        } else { roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException(ROLE_NOTSET))); }

        user.setRoles(roles);
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered Successfully"));
    }
}
