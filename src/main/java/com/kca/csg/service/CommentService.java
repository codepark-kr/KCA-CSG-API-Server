package com.kca.csg.service;

import com.kca.csg.model.Comment;
import com.kca.csg.payload.request.CommentRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.UserPrincipal;

public interface CommentService {

    PagedResponse<Comment> getAllComments(Long twinsId, int page, int size);

    Comment addComment(CommentRequest commentRequest, Long twinsId, UserPrincipal currentUser);
    Comment getComment(Long twinsId, Long id);
    Comment updateComment(Long twinsId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

    ApiResponse deleteComment(Long twinsId, Long id, UserPrincipal currentUser);
}
