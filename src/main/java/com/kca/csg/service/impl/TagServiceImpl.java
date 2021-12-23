package com.kca.csg.service.impl;

import com.kca.csg.exception.ResourceNotFoundException;
import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Tag;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.TagRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.*;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;
    public TagServiceImpl(TagRepository tagRepository){ this.tagRepository = tagRepository; }


    @Override
    public PagedResponse<Tag> getAllTags(int page, int size) {
        pageValidation(page, size);
        Page<Tag> tags = tagRepository.findAll(sortDescending(page, size));
        List<Tag> content = tags.getNumberOfElements() == 0 ? Collections.emptyList() : tags.getContent();

        return new PagedResponse<>(content, tags.getNumber(), tags.getSize(), tags.getTotalElements(), tags.getTotalPages(), tags.isLast());
    }

    @Override
    public Tag getTag(Long id) { return findTagById(id); }

    @Override
    public Tag addTag(Tag tag, UserPrincipal currenUser) { return tagRepository.save(tag); }

    @Override
    public Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser) {
        Tag tag = findTagById(id);

        assert tag != null;
        if(tag.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            tag.setName(newTag.getName());
            return tagRepository.save(tag);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "edit this tag");
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public ApiResponse deleteTag(Long id, UserPrincipal currentUser) {
        Tag tag = findTagById(id);

        assert tag != null;
        if(tag.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            tagRepository.deleteById(id);

            return new ApiResponse(Boolean.TRUE, SUCCESS_DELETE + "tag");
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO + "delete this tag");
        throw new UnauthorizedException(apiResponse);
    }

    public Tag findTagById(Long id){ return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id)); }
}
