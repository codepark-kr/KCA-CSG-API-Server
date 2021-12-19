package com.kca.csg.service.impl;

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
import com.kca.csg.repository.TagRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.service.TwinsService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.kca.csg.security.UserPrincipal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.AppConstants.*;
import static com.kca.csg.util.GlobalUtils.findResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class TwinsServiceImpl implements TwinsService {

    private final TwinsRepository twinsRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    public TwinsServiceImpl(TwinsRepository twinsRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.twinsRepository = twinsRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public PagedResponse<Twins> getAllTwins(int page, int size){
        pageValidation(page, size);
        Page<Twins> twins = twinsRepository.findAll(sortDescending(page, size));
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByCreatedBy(String username, int page, int size){
        pageValidation(page, size);
        User user = userRepository.getUserByName(username);
        Page<Twins> twins = twinsRepository.findByCreatedBy(user.getId(), sortDescending(page, size));
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByCategory(Long id, int page, int size){
        pageValidation(page, size);
        Category category = (Category) findResourceById(id, CATEGORY, ID);
        sortDescending(page, size);
        assert category != null;
        Page<Twins> twins = twinsRepository.findByCategory(category.getId(), sortDescending(page, size));
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public PagedResponse<Twins> getTwinsByTag(Long id, int page, int size){
        pageValidation(page, size);
        Tag tag = (Tag) findResourceById(id, TAG, ID);
        sortDescending(page, size);
        Page<Twins> twins = twinsRepository.findByTagsIn(Collections.singletonList(tag), sortDescending(page, size));
        List<Twins> content = twins.getNumberOfElements() == 0 ? Collections.emptyList() : twins.getContent();

        return new PagedResponse<>(content, twins.getNumber(), twins.getSize(), twins.getTotalElements(), twins.getTotalPages(), twins.isLast());
    }

    @Override
    public Twins updateTwins(Long id, TwinsRequest newTwinsRequest, UserPrincipal currentUser){
        Twins twins = (Twins) findResourceById(id, TWINS, id);
        Category category = (Category) findResourceById(newTwinsRequest.getCategoryId(), CATEGORY, newTwinsRequest.getCategoryId());

        assert twins != null;
        if(twins.getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            Twins.builder()
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
        Twins twins = (Twins) findResourceById(id, TWINS, id);

        assert twins != null;
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
        User user = (User) findResourceById(currentUser.getId(), USER, 1L);
        Category category = (Category) findResourceById(twinsRequest.getCategoryId(), CATEGORY, twinsRequest.getCategoryId());
        List<Tag> tags = new ArrayList<>(twinsRequest.getTags().size());

        for(String name : twinsRequest.getTags()){
            Tag tag = tagRepository.findByName(name);
            tag = tag == null ? tagRepository.save(new Tag(name)) : tag;
            tags.add(tag);
        }

        Twins twins = new Twins();
        Twins.builder().korTitle(twinsRequest.getKorTitle()).korContent(twinsRequest.getKorContent())
                .engTitle(twinsRequest.getEngTitle()).engContent(twinsRequest.getEngContent())
                .category(category).user(user).tags(tags).build();

        Twins newTwins = twinsRepository.save(twins);
        TwinsResponse twinsResponse = new TwinsResponse();

        TwinsResponse.builder().korTitle(newTwins.getKorTitle()).korContent(newTwins.getKorContent())
                .engTitle(newTwins.getEngTitle()).engContent(newTwins.getEngContent())
                .category(newTwins.getCategory().getName()).build();

        List<String> tagNames = new ArrayList<>(newTwins.getTags().size());
        for(Tag tag : newTwins.getTags()){ tagNames.add(tag.getName()); }
        twinsResponse.setTags(tagNames);

        return twinsResponse;
    }

    @Override
    public Twins getTwins(Long id){
        return (Twins) findResourceById(id, USER, id);
    }
}
