package com.kca.csg.service;

import com.kca.csg.model.Tag;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.UserPrincipal;

public interface TagService {

    PagedResponse<Tag> getAllTags(int page, int size);

    Tag getTag(Long id);
    Tag addTag(Tag tag, UserPrincipal currenUser);
    Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser);

    ApiResponse deleteTag(Long id, UserPrincipal currentUser);
}
