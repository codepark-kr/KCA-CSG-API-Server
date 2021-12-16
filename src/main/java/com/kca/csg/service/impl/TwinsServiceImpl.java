package com.kca.csg.service.impl;

import com.kca.csg.exception.BadRequestException;
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
import com.kca.csg.payload.response.TwinsResponse;
import com.kca.csg.repository.CategoryRepository;
import com.kca.csg.repository.TagRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.service.TwinsService;
import com.kca.csg.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.kca.csg.security.UserPrincipal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.AppConstants.*;
import static com.kca.csg.util.ValidationUtils.validatePageNumberAndSize;

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

        validatePageNumberAndSize(page, size);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Twins> twins = twinsRepository.findByCategory(category.getId(), pageable);
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByTag(Long id, int page, int size){

        validatePageNumberAndSize(page, size);
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
            twins.builder()
            .korTitle(newTwinsRequest.getKorTitle())
            .korContent(newTwinsRequest.getKorContent())
            .engTitle(newTwinsRequest.getEngTitle())
            .engContent(newTwinsRequest.getEngContent())
            .category(category).build();

            return twinsRepository.save(twins);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to edit this post");
        throw new UnauthorizedException(apiResponse);
    }

    public ApiResponse deleteTwins(Long id, UserPrincipal currentUser){
        Twins twins = twinsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TWINS, ID, id));

        if(twins.getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
         twinsRepository.deleteById(id);

         return new ApiResponse(Boolean.TRUE, "You successfully deleted the post");
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete this post");
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public TwinsResponse addTwins(TwinsRequest twinsRequest, UserPrincipal currentUser){
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException(USER, ID, 1L));
        Category category = categoryRepository.findById(twinsRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, twinsRequest.getCategoryId()));

        List<Tag> tags = new ArrayList<>(twinsRequest.getTags().size());

        for(String name : twinsRequest.getTags()){
            Tag tag = tagRepository.findByName(name);
            tag = tag == null ? tagRepository.save(new Tag(name)) : tag;

            tags.add(tag);
        }

        Twins twins = new Twins();
        twins.builder().korTitle(twinsRequest.getKorTitle()).korContent(twinsRequest.getKorContent())
                .engTitle(twinsRequest.getEngTitle()).engContent(twinsRequest.getEngContent())
                .category(category).user(user).tags(tags).build();

        Twins newTwins = twinsRepository.save(twins);
        TwinsResponse twinsResponse = new TwinsResponse();

        twinsResponse.builder().korTitle(newTwins.getKorTitle()).korContent(newTwins.getKorContent())
                .engTitle(newTwins.getEngTitle()).engContent(newTwins.getEngContent())
                .category(newTwins.getCategory().getName()).build();

        List<String> tagNames = new ArrayList<>(newTwins.getTags().size());
        for(Tag tag : newTwins.getTags()){ tagNames.add(tag.getName()); }
        twinsResponse.setTags(tagNames);

        return twinsResponse;
    }

    @Override
    public Twins getTwins(Long id){
        return twinsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TWINS, ID, id));
    }

}
