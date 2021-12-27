package com.kca.csg.service.impl;


import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Album;
import com.kca.csg.model.Photo;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.PhotoRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.PhotoResponse;
import com.kca.csg.repository.AlbumRepository;
import com.kca.csg.repository.PhotoRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.PhotoService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.getResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }


    @Override
    public PagedResponse<PhotoResponse> getAllPhotos(int page, int size) {
        pageValidation(page, size);
        Page<Photo> photos = photoRepository.findAll(sortDescending(page, size));

        List<PhotoResponse> photoResponses = new ArrayList<>(photos.getContent().size());
        for(Photo photo : photos.getContent()){
            photoResponses.add(new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl(), photo.getAlbum().getId()));
        }

        assert photos != null;
        if(photos.getNumberOfElements() == 0){
            return new PagedResponse<>(Collections.emptyList(), photos.getNumber(), photos.getSize(), photos.getTotalElements(), photos.getTotalPages(), photos.isLast());
        }
        return new PagedResponse<>(photoResponses, photos.getNumber(), photos.getSize(), photos.getTotalElements(), photos.getTotalPages(), photos.isLast());
    }

    @Override
    public PhotoResponse getPhoto(Long id) {
        Photo photo = (Photo) getResourceById(id, PHOTO, id);

        return new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl(), photo.getAlbum().getId());
    }

    @Override
    public PhotoResponse updatePhoto(Long id, PhotoRequest photoRequest, UserPrincipal currentUser) {
        Album album = (Album) getResourceById(photoRequest.getAlbumId(), ALBUM, photoRequest.getAlbumId());
        Photo photo = (Photo) getResourceById(id, PHOTO, id);

        assert photo != null;
        if(photo.getAlbum().getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString())) ){
        photo.builder().title(photoRequest.getTitle()).thumbnailUrl(photoRequest.getThumbnailUrl()).album(album).build();
        Photo updatedPhoto = photoRepository.save(photo);

        return new PhotoResponse(updatedPhoto.getId(), updatedPhoto.getTitle(), updatedPhoto.getUrl(), updatedPhoto.getThumbnailUrl(), updatedPhoto.getAlbum().getId());
        }
        throw new UnauthorizedException(new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "update this photo"));
    }

    @Override
    public PhotoResponse addPhoto(PhotoRequest photoRequest, UserPrincipal currentUser) {
        Album album = (Album) getResourceById(photoRequest.getAlbumId(), ALBUM, photoRequest.getAlbumId());

        assert album != null;
        if(album.getUser().getId().equals(currentUser.getId())){
            Photo newPhoto = photoRepository.save(new Photo(photoRequest.getTitle(), photoRequest.getUrl(), photoRequest.getThumbnailUrl(), album));
            return new PhotoResponse(newPhoto.getId(), newPhoto.getTitle(), newPhoto.getUrl(), newPhoto.getThumbnailUrl(), newPhoto.getAlbum().getId());
        }
        throw new UnauthorizedException(new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "add photo in this album"));
    }

    @Override
    public ApiResponse deletePhoto(Long id, UserPrincipal currentUser) {
        Photo photo = (Photo) getResourceById(id, PHOTO, id);
        if(photo.getAlbum().getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            photoRepository.deleteById(id);
            return new ApiResponse(Boolean.TRUE, SUCCESS_DELETE);
        }
        throw new UnauthorizedException(new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "delete this photo"));
    }

    @Override
    public PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size) {
        pageValidation(page, size);
        Page<Photo> photos = photoRepository.findByAlbumId(albumId, sortDescending(page, size));

        List<PhotoResponse> photoResponses = new ArrayList<>(photos.getContent().size());
        for(Photo photo : photos.getContent()){
            photoResponses.add(new PhotoResponse(photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl(), photo.getAlbum().getId()));
        }
        return new PagedResponse<>(photoResponses, photos.getNumber(), photos.getSize(), photos.getTotalElements(), photos.getTotalPages(), photos.isLast());
    }
}
