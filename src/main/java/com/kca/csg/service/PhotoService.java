package com.kca.csg.service;

import com.kca.csg.payload.request.PhotoRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.PhotoResponse;
import com.kca.csg.security.UserPrincipal;

public interface PhotoService {

    PagedResponse<PhotoResponse> getAllPhotos(int page, int size);

    PhotoResponse getPhoto(Long id);
    PhotoResponse updatePhoto(Long id, PhotoResponse photoResponse, UserPrincipal currentUser);
    PhotoResponse addPhoto(PhotoRequest photoRequest, UserPrincipal currentUser);

    ApiResponse deletePhoto(Long id, UserPrincipal currentUser);

    PagedResponse<PhotoResponse> getAllPhotosByAlbum(Long albumId, int page, int size);

}
