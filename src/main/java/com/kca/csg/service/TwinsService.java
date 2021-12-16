package com.kca.csg.service;

import com.kca.csg.model.Twins;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.request.TwinsRequest;
import com.kca.csg.payload.response.TwinsResponse;
import com.kca.csg.security.UserPrincipal;

public interface TwinsService {

    PagedResponse<Twins> getAllTwins(int page, int size);

    PagedResponse<Twins> getTwinsByCreatedBy(String username, int page, int size);

    PagedResponse<Twins> getTwinsByCategory(Long id, int page, int size);

    PagedResponse<Twins> getTwinsByTag(Long id, int page, int size);

    Twins updateTwins(Long id, TwinsRequest newTwinsRequest, UserPrincipal currentUser);

    TwinsResponse deleteTwins(Long id, UserPrincipal currentUser);

    TwinsResponse addTwins(TwinsRequest twinsRequest, UserPrincipal currentUser);

    Twins getTwins(Long id);
}
