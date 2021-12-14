package com.kca.csg.service;

import com.kca.csg.model.Twins;
import com.kca.csg.payload.PagedResponse;
import com.kca.csg.payload.TwinsRequest;
import com.kca.csg.payload.TwinsResponse;
import com.kca.csg.repository.TwinsRepository;
import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TwinsService {

    PagedResponse<Twins> getAllTwins(int page, int size);

    PagedResponse<Twins> getTwinsByCreatedBy(String username, int page, int size);

    PagedResponse<Twins> getTwinsCategory(Long id, int page, int size);

    PagedResponse<Twins> getTwinsByTag(Long id, int page, int size);

    Twins updateTwins(Long id, TwinsRequest newTwinsRequest, UserPrincipal currentUser);

    TwinsResponse deleteTwins(Long id, UserPrincipal currentUser);

    TwinsResponse addTwins(TwinsRequest twinsRequest, UserPrincipal currentUser);

    Twins getTwins(Long id);
}
