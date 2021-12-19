package com.kca.csg.controller;


import com.kca.csg.model.Album;
import com.kca.csg.model.Twins;
import com.kca.csg.model.User;
import com.kca.csg.payload.UserIdentityAvailability;
import com.kca.csg.payload.UserProfile;
import com.kca.csg.payload.UserSummary;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.AlbumService;
import com.kca.csg.service.TwinsService;
import com.kca.csg.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.kca.csg.util.AppConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final TwinsService twinsService;
    private final AlbumService albumService;

    public UserController(UserService userService, TwinsService twinsService, AlbumService albumService) {
        this.userService = userService;
        this.twinsService = twinsService;
        this.albumService = albumService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserSummary userSummary = userService.getCurrentUser(currentUser);

        return new ResponseEntity<>(userSummary, HttpStatus.OK);
    }

    @GetMapping("/checkName")
    public ResponseEntity<UserIdentityAvailability> checkUserNameAvailability(@RequestParam(value = "username") String username){
        UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(username);

        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email){
        UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email);

        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "username") String username){
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/twins/{username}")
    public ResponseEntity<PagedResponse<Twins>> getTwinsCreatedBy(@PathVariable(value = "username") String username,
             @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
             @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        PagedResponse<Twins> response = twinsService.getTwinsByCreatedBy(username, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/albums/{username}")
    public ResponseEntity<PagedResponse<Album>> getUserAlbums(@PathVariable(value = "username") String username,
             @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
             @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        PagedResponse<Album> response = albumService.getUserAlbums(username, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User newUser,
             @PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser){
        User updatedUser = userService.updateUser(newUser, username, currentUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser){
        ApiResponse apiResponse = userService.deleteUser(username, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/grantAdmin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> grantAdmin(@PathVariable(name = "username") String username){
        ApiResponse apiResponse = userService.grantAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/retrieveAdmin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> retrieveAdmin(@PathVariable(name = "username") String username){
        ApiResponse apiResponse = userService.retrieveAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
