package com.kca.csg.repository;

import com.kca.csg.exception.ResourceNotFoundException;
import com.kca.csg.model.User;
import com.kca.csg.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NotBlank String username);
    Optional<User> findByEmail(@NotBlank String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(@NotBlank String username);
    Boolean existsByEmail(@NotBlank String email);

    default User getUser(UserPrincipal currentUser){ return getUserByName(currentUser.getUsername()); }
    default User getUserByName(String username){
        return findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username)); }
}
