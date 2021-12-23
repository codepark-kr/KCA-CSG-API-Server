package com.kca.csg.controller;

import com.kca.csg.model.Shorties;
import com.kca.csg.payload.request.ShortiesRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.ShortiesResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.ShortiesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.Constants.DEFAULT_PAGE_NUMBER;
import static com.kca.csg.util.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/short")
public class ShortiesController {

    private final ShortiesService shortiesService;
    public ShortiesController(ShortiesService shortiesService){ this.shortiesService = shortiesService;  }


    @GetMapping
    @ApiOperation(value = "Get all shorties the type of post")
    public ResponseEntity<PagedResponse<Shorties>> getAllShorties(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
    PagedResponse<Shorties> response = shortiesService.getAllShorties(page, size);

    return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Add single shorties the type of post")
    public ResponseEntity<ShortiesResponse> addShorties(@Valid @RequestBody ShortiesRequest shortiesRequest,
                                                        @CurrentUser UserPrincipal currentUser){
    ShortiesResponse shortiesResponse = shortiesService.addShorties(shortiesRequest, currentUser);

    return new ResponseEntity<>(shortiesResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get specific shorties the type of post")
    public ResponseEntity<Shorties> getShorties(@PathVariable(name = "id") Long id){
        Shorties shorties = shortiesService.getShorties(id);

        return new ResponseEntity<>(shorties, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Update specific shorties the type of post")
    public ResponseEntity<Shorties> updateShorties(@PathVariable(name = "id") Long id,
             @Valid @RequestBody ShortiesRequest newShortiesRequest, @CurrentUser UserPrincipal currentUser){
        Shorties shorties = shortiesService.updateShorties(id, newShortiesRequest, currentUser);

        return new ResponseEntity<>(shorties, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ApiOperation(value = "Delete specific shorties the type of post")
    public ResponseEntity<ApiResponse> deleteShorties(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser){
        ApiResponse apiResponse = shortiesService.deleteShorties(id, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
