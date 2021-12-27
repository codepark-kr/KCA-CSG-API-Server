package com.kca.csg.controller;


import com.kca.csg.exception.ResponseEntityErrorException;
import com.kca.csg.payload.response.AlbumResponse;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.service.AlbumService;
import com.kca.csg.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final PhotoService photoService;


    public AlbumController(AlbumService albumService, PhotoService photoService) {
        this.albumService = albumService;
        this.photoService = photoService;
    }

    @ExceptionHandler(ResponseEntityErrorException.class)
    public ResponseEntity<ApiResponse> handleExceptions(ResponseEntityErrorException exception){
        return exception.getApiResponse();
    }

    @GetMapping
    public PagedResponse<AlbumResponse> getAllAlbums(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        pageValidation(page, size);

        return albumService.getAllAlbums(page, size);
    }
}
