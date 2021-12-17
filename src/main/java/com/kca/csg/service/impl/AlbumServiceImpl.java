package com.kca.csg.service.impl;

import com.kca.csg.model.Album;
import com.kca.csg.payload.request.AlbumRequest;
import com.kca.csg.payload.response.AlbumResponse;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.AlbumRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.AlbumService;
import org.springframework.http.ResponseEntity;
import springfox.documentation.swagger2.mappers.ModelMapper;

import static com.kca.csg.util.GlobalUtils.sortDescending;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedResponse<Album> getUserAlbums(String username, int page, int size) {
        sortDescending(page, size);
    }

    @Override
    public PagedResponse<AlbumResponse> getAllAlbums(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public ResponseEntity<Album> getAlbum(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deleteAlbum(Long id, UserPrincipal currentuser) {
        return null;
    }
}
