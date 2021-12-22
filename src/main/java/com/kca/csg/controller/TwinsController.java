package com.kca.csg.controller;

import com.kca.csg.model.Twins;
import com.kca.csg.payload.request.TwinsRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.TwinsResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.TwinsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.Constants.DEFAULT_PAGE_NUMBER;
import static com.kca.csg.util.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/twins")
public class TwinsController {

    private final TwinsService twinsService;

    public TwinsController(TwinsService twinsService) {
        this.twinsService = twinsService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Twins>> getAllTwins(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size){
        PagedResponse<Twins> response = twinsService.getAllTwins(page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<PagedResponse<Twins>> getTwinsByCategory(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @PathVariable(name = "id") Long id){
        PagedResponse<Twins> response = twinsService.getTwinsByCategory(id, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<PagedResponse<Twins>> getTwinsByTag(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            @PathVariable(name = "id") Long id){
        PagedResponse<Twins> response = twinsService.getTwinsByTag(id, page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TwinsResponse> addTwins(@Valid @RequestBody TwinsRequest twinsRequest,
             @CurrentUser UserPrincipal currentUser){
        TwinsResponse twinsResponse = twinsService.addTwins(twinsRequest, currentUser);

        return new ResponseEntity<>(twinsResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Twins> getTwins(@PathVariable(name = "id") Long id){
        Twins twins = twinsService.getTwins(id);

        return new ResponseEntity<>(twins, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Twins> updateTwins(@PathVariable(name = "id") Long id,
             @Valid @RequestBody TwinsRequest newTwinsRequest, @CurrentUser UserPrincipal currentUser){
        Twins twins = twinsService.updateTwins(id, newTwinsRequest, currentUser);

        return new ResponseEntity<>(twins, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteTwins(@PathVariable(name = "id") Long id, @CurrentUser UserPrincipal currentUser){
        ApiResponse apiResponse = twinsService.deleteTwins(id, currentUser);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
