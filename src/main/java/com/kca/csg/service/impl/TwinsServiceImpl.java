package com.kca.csg.service.impl;

import com.kca.csg.exception.ResourceNotFoundException;
import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Category;
import com.kca.csg.model.Tag;
import com.kca.csg.model.Twins;
import com.kca.csg.model.User;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.TwinsRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.CategoryRepository;
import com.kca.csg.repository.TagRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.service.TwinsService;
import com.kca.csg.util.AppUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.kca.csg.security.UserPrincipal;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.kca.csg.util.AppConstants.*;
import static com.kca.csg.util.AppUtils.validatePageNumberAndSize;

@Service
public class TwinsServiceImpl implements TwinsService {

    private final TwinsRepository twinsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    public TwinsServiceImpl(TwinsRepository twinsRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.twinsRepository = twinsRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public PagedResponse<Twins> getAllTwins(int page, int size){
        validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Twins> twins = twinsRepository.findAll(pageable);
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByCreatedBy(String username, int page, int size){
        validatePageNumberAndSize(page, size);
        User user = userRepository.getUserByName(username);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Twins> twins = twinsRepository.findByCreatedBy(user.getId(), pageable);
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByCategory(Long id, int page, int size){

        AppUtils.validatePageNumberAndSize(page, size);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Twins> twins = twinsRepository.findByCategory(category.getId(), pageable);
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByTag(Long id, int page, int size){

        AppUtils.validatePageNumberAndSize(page, size);
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TAG, ID, id));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Twins> twins = twinsRepository.findByTags(Collections.singletonList(tag), pageable);
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public Twins updateTwins(Long id, TwinsRequest newTwinsRequest, UserPrincipal currentUser){
        Twins twins = twinsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TWINS, ID, id));
        Category category = categoryRepository.findById(newTwinsRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, newTwinsRequest.getCategoryId()));

        if(twins.getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            twins.setKorTitle(newTwinsRequest.getKorTitle());
            twins.setKorContent(newTwinsRequest.getKorContent());
            twins.setEngTitle(newTwinsRequest.getEngTitle());
            twins.setEngContent(newTwinsRequest.getEngContent());
            twins.setCategory(category);

            return twinsRepository.save(twins);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to edit this post");
        throw new UnauthorizedException(apiResponse);
    }
}
