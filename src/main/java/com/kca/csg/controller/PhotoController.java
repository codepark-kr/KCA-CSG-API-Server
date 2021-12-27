package com.kca.csg.controller;

import com.kca.csg.payload.request.PhotoRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.PhotoResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.Constants.*;

@RestController
@RequestMapping("/api/photos")
public class PhotoController{

    private final PhotoService photoService;


    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public PagedResponse<PhotoResponse> getAllPhotos(@RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
                                                     @RequestParam(name = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){

        return photoService.getAllPhotos(page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PhotoResponse> addPhoto(@Valid @RequestBody PhotoRequest photoRequest, @CurrentUser UserPrincipal currentUser){

        return new ResponseEntity<>(photoService.addPhoto(photoRequest, currentUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponse> getPhoto(@PathVariable(name = "id") Long id){

        return new ResponseEntity<>(photoService.getPhoto(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<PhotoResponse> updatePhoto(@PathVariable(name = "id") Long id,
                                                     @Valid @RequestBody PhotoRequest photoRequest, @CurrentUser UserPrincipal currentUser){
        return new ResponseEntity<>(photoService.updatePhoto(id, photoRequest, currentUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser){
        return new ResponseEntity<>(photoService.deletePhoto(id, currentUser), HttpStatus.OK);
    }
}
