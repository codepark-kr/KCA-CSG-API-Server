package com.kca.csg.service.impl;

import com.kca.csg.exception.ApiException;
import com.kca.csg.model.Album;
import com.kca.csg.model.User;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.AlbumRequest;
import com.kca.csg.payload.response.AlbumResponse;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.AlbumRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.AppConstants.ALBUM;
import static com.kca.csg.util.AppConstants.NO_PERMISSION;
import static com.kca.csg.util.GlobalUtils.findResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

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
        User user = userRepository.getUserByName(username);
        sortDescending(page, size);
        Page<Album> albums = albumRepository.findByCreatedBy(user.getId(), sortDescending(page, size));
        List<Album> content = albums.getNumberOfElements() > 0 ? albums.getContent() : Collections.emptyList();

        return new PagedResponse<>(content, albums.getNumber(), albums.getSize(), albums.getTotalElements(),
                albums.getTotalPages(), albums.isLast());
    }

    @Override
    public PagedResponse<AlbumResponse> getAllAlbums(int page, int size) {
        pageValidation(page, size);
        sortDescending(page, size);
        Page<Album> albums = albumRepository.findAll(sortDescending(page, size));

        assert albums != null;
        if(albums.getNumberOfElements() == 0){
            return new PagedResponse<>(Collections.emptyList(), albums.getNumber(), albums.getSize(), albums.getTotalElements(),
                    albums.getTotalPages(), albums.isLast());
        }
        List<AlbumResponse> albumResponses = Arrays.asList(modelMapper.map(albums.getContent(), AlbumResponse[].class));
        return new PagedResponse<>(albumResponses, albums.getNumber(), albums.getSize(), albums.getTotalElements(),
                albums.getTotalPages(), albums.isLast());
    }

    @Override
    public ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Album album = new Album();
        modelMapper.map(albumRequest, album);
        album.setUser(user);
        Album newAlbum = albumRepository.save(album);

        return new ResponseEntity<>(newAlbum, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Album> getAlbum(Long id) {
        return new ResponseEntity<>((Album) findResourceById(id, ALBUM, id), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserPrincipal currentUser) {
        Album album = (Album) findResourceById(id, ALBUM, id);
        User user = userRepository.getUser(currentUser);

        assert album != null;
        if(album.getUser().getId().equals(user.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            album.setTitle(newAlbum.getTitle());
            Album updatedAlbum = albumRepository.save(album);
            AlbumResponse albumResponse = new AlbumResponse();
            modelMapper.map(updatedAlbum, albumResponse);
            return new ResponseEntity<>(albumResponse, HttpStatus.OK);
        }
        throw new ApiException(HttpStatus.UNAUTHORIZED, NO_PERMISSION);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteAlbum(Long id, UserPrincipal currentUser) {
        Album album = (Album) findResourceById(id, ALBUM, id);
        User user = userRepository.getUser(currentUser);

        assert album != null;
        if(album.getUser().getId().equals(user.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
        albumRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted album"), HttpStatus.OK);
        }
        throw new ApiException(HttpStatus.UNAUTHORIZED, NO_PERMISSION);
    }
}
