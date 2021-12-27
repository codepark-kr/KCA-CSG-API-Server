package com.kca.csg.service.impl;

import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Shorties;
import com.kca.csg.model.User;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.ShortiesRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.payload.response.ShortiesResponse;
import com.kca.csg.repository.ShortiesRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.ShortiesService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.getResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class ShortiesServiceImpl implements ShortiesService {

    private final ShortiesRepository shortiesRepository;
    private final UserRepository userRepository;

    public ShortiesServiceImpl(ShortiesRepository shortiesRepository, UserRepository userRepository) {
        this.shortiesRepository = shortiesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PagedResponse<Shorties> getAllShorties(int page, int size) {
        pageValidation(page, size);
        Page<Shorties> shorties = shortiesRepository.findAll(sortDescending(page, size));
        List<Shorties> content = shorties.getNumberOfElements() == 0 ? Collections.emptyList() : shorties.getContent();

        return new PagedResponse<>(content, shorties.getNumber(), shorties.getSize(), shorties.getTotalElements(), shorties.getTotalPages(), shorties.isLast());
    }

    @Override
    public PagedResponse<Shorties> getShortiesByCreatedBy(String username, int page, int size) {
        pageValidation(page, size);
        User user = userRepository.getUserByName(username);
        Page<Shorties> shorties = shortiesRepository.findByCreatedBy(user.getId(), sortDescending(page, size));
        List<Shorties> content = shorties.getNumberOfElements() == 0 ? Collections.emptyList() : shorties.getContent();

        return new PagedResponse<>(content, shorties.getNumber(), shorties.getSize(), shorties.getTotalElements(), shorties.getTotalPages(), shorties.isLast());
    }

    @Override
    public Shorties updateShorties(Long id, ShortiesRequest newShortiesRequest, UserPrincipal currentUser) {
        Shorties shorties = (Shorties) getResourceById(id, SHORTIES, id);

        assert shorties != null;
        if(shorties.getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
        Shorties newShorties = Shorties.builder()
                .firstThreadId(newShortiesRequest.getFirstThreadId())
                .threadOrder(newShortiesRequest.getThreadOrder())
                .title(newShortiesRequest.getTitle())
                .content(newShortiesRequest.getContent()).build();

        return shortiesRepository.save(newShorties);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "edit this post");
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public Shorties getShorties(Long id) { return (Shorties) getResourceById(id, SHORTIES, id); }

    @Override
    public ShortiesResponse addShorties(ShortiesRequest newShortiesRequest, UserPrincipal currentUser) {
        User user = (User) getResourceById(currentUser.getId(), USER, 1L);

        Shorties shorties = Shorties.builder()
                .firstThreadId(newShortiesRequest.getFirstThreadId())
                .threadOrder(newShortiesRequest.getThreadOrder())
                .title(newShortiesRequest.getTitle())
                .content(newShortiesRequest.getContent()).user(user).build();

        Shorties newShorties = shortiesRepository.save(shorties);

        ShortiesResponse shortiesResponse = ShortiesResponse.builder()
                .firstThreadId(newShorties.getFirstThreadId())
                .threadOrder(newShorties.getThreadOrder())
                .title(newShorties.getTitle())
                .content(newShorties.getContent()).build();

        return shortiesResponse;
    }

    @Override
    public ApiResponse deleteShorties(Long id, UserPrincipal currentUser) {
        Shorties shorties = (Shorties) getResourceById(id, SHORTIES, id);

        assert shorties != null;
        if(shorties.getUser().getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
        shortiesRepository.deleteById(id);
        return new ApiResponse(Boolean.TRUE, SUCCESS_DELETE + "post");
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "delete this post");
        throw new UnauthorizedException(apiResponse);
    }
}
