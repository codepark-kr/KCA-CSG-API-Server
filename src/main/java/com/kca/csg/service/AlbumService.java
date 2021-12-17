package com.kca.csg.service;

import com.kca.csg.model.Album;
import com.kca.csg.payload.request.AlbumRequest;
import com.kca.csg.payload.response.AlbumResponse;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface AlbumService {

    PagedResponse<Album> getUserAlbums(String username, int page, int size);
    PagedResponse<AlbumResponse> getAllAlbums(int page, int size);

    ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserPrincipal currentUser);
    ResponseEntity<Album> getAlbum(Long id);
    ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserPrincipal currentUser);
    ResponseEntity<ApiResponse> deleteAlbum(Long id, UserPrincipal currentUser);

}
