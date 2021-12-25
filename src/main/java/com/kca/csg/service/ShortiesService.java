package com.kca.csg.service;

import com.kca.csg.model.Shorties;
import com.kca.csg.payload.request.ShortiesRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.ShortiesResponse;
import com.kca.csg.security.UserPrincipal;

public interface ShortiesService {

    PagedResponse<Shorties> getAllShorties(int page, int size);
    PagedResponse<Shorties> getShortiesByCreatedBy(String username, int page, int size);

    Shorties updateShorties(Long id, ShortiesRequest newShortiesRequest, UserPrincipal currentUser);
    Shorties getShorties(Long id);

    ShortiesResponse addShorties(ShortiesRequest newShortiesRequest, UserPrincipal currentUser);
    ApiResponse deleteShorties(Long id, UserPrincipal currentUser);
}
