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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.Constants.*;

@Slf4j
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
    @ApiOperation(value = "Get summary of current user")
    public ResponseEntity<UserSummary> getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserSummary userSummary = userService.getCurrentUser(currentUser);

        return new ResponseEntity<>(userSummary, HttpStatus.OK);
    }

    @GetMapping("/checkName")
    @ApiOperation(value = "Check availability of specific username")
    public ResponseEntity<UserIdentityAvailability> checkUserNameAvailability(@RequestParam(value = "username") String username){
        UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(username.substring(10));

        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/checkEmail")
    @ApiOperation(value = "Check availability of specific email")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email){
        UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email.substring(7));

        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    @ApiOperation(value = "Get all information of current user")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "username") String username){
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/twins/{username}")
    @ApiOperation(value = "Get twins the type of posts by this current user")
    public ResponseEntity<PagedResponse<Twins>> getTwinsCreatedBy(@PathVariable(value = "username") String username,
             @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
             @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        PagedResponse<Twins> response = twinsService.getTwinsByCreatedBy(username, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/albums/{username}")
    @ApiOperation(value = "Get album of this current user")
    public ResponseEntity<PagedResponse<Album>> getUserAlbums(@PathVariable(value = "username") String username,
             @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
             @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        PagedResponse<Album> response = albumService.getUserAlbums(username, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Add user as user-role by current user with admin-authority")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Update information of specific user by current user with admin-authority")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User newUser,
             @PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser){
        User updatedUser = userService.updateUser(newUser, username, currentUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Delete account of specific user by current user with admin-authority")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username, @CurrentUser UserPrincipal currentUser){
        ApiResponse apiResponse = userService.deleteUser(username, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/grantAdmin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Grant admin-role to specific user by current user with admin-authority")
    public ResponseEntity<ApiResponse> grantAdmin(@PathVariable(name = "username") String username){
        ApiResponse apiResponse = userService.grantAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/retrieveAdmin/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Retrieve admin-role to specific user by current user with admin-authority")
    public ResponseEntity<ApiResponse> retrieveAdmin(@PathVariable(name = "username") String username){
        ApiResponse apiResponse = userService.retrieveAdmin(username);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}