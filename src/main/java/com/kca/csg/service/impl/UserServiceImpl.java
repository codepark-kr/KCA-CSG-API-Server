package com.kca.csg.service.impl;


import com.kca.csg.exception.*;
import com.kca.csg.model.User;
import com.kca.csg.model.role.Role;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.UserIdentityAvailability;
import com.kca.csg.payload.UserProfile;
import com.kca.csg.payload.UserSummary;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.repository.RoleRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TwinsRepository twinsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, TwinsRepository twinsRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.twinsRepository = twinsRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser){
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName());
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username){
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email){
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserProfile getUserProfile(String username){
        User user = userRepository.getUserByName(username);
        Long postCount = twinsRepository.countByCreatedBy(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getCreatedAt(), user.getEmail(), user.getContact(), postCount);
    }

    @Override
    public User addUser(User user){
        if(userRepository.existsByUsername(user.getUsername())){
                ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
                throw new BadRequestException(apiResponse);
            }
        if(userRepository.existsByEmail(user.getEmail())){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse);
        }
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                () -> new AppException("User role not set")));
        user.builder().roles(roles).password(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser, String username, UserPrincipal currentUser){
        User user = userRepository.getUserByName(username);
        if(user.getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            user.builder()
                    .firstName(newUser.getFirstName())
                    .lastName(newUser.getLastName())
                    .password(newUser.getPassword())
                    .contact(newUser.getContact()).build();

            return userRepository.save(user);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update profile of : " + username);
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public ApiResponse deleteUser(String username, UserPrincipal currentUser){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", username));

        if(!user.getId().equals(currentUser.getId() )
                || !currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You dont' have permission to delete profile of : " + username);
            throw new AccessDeniedException(apiResponse);
        }
        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of : "+username);
    }

    @Override
    public ApiResponse grantAdmin(String username){
        User user = userRepository.getUserByName(username);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User role not set")));
        roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.builder().roles(roles).build();
        userRepository.save(user);

        return new ApiResponse(Boolean.TRUE, "You gave ADMIN role to user : "+ username);
    }

    @Override
    public ApiResponse removeAdmin(String username){
        User user = userRepository.getUserByName(username);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.builder().roles(roles).build();
        userRepository.save(user);

        return new ApiResponse(Boolean.TRUE, "You took ADMIN role from user : "+ username);
    }
}
