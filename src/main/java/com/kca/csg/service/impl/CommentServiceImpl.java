package com.kca.csg.service.impl;

import com.kca.csg.exception.ApiException;
import com.kca.csg.model.Comment;
import com.kca.csg.model.Twins;
import com.kca.csg.model.User;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.request.CommentRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.CommentRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.getResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TwinsRepository twinsRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TwinsRepository twinsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.twinsRepository = twinsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PagedResponse<Comment> getAllComments(Long twinsId, int page, int size) {
        pageValidation(page, size);
        Page<Comment> comments = commentRepository.findByTwinsId(twinsId, (Pageable) sortDescending(page, size));

        return new PagedResponse<>(comments.getContent(), comments.getNumber(), comments.getSize(), comments.getTotalElements(), comments.getTotalPages(), comments.isLast());
    }

    @Override
    public Comment addComment(CommentRequest commentRequest, Long twinsId, UserPrincipal currentUser) {
        Twins twins = (Twins) getResourceById(twinsId, TWINS, twinsId);
        User user = userRepository.getUser(currentUser);
        Comment comment = new Comment(commentRequest.getBody());
        comment.builder().user(user).twins(twins).name(currentUser.getUsername()).email(currentUser.getEmail()).build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Long twinsId, Long id) {
        Twins twins = (Twins) getResourceById(twinsId, TWINS, twinsId);
        Comment comment = (Comment) getResourceById(id, COMMENT, id);
        if(comment.getTwins().getId().equals(twins.getId())){ return comment; }

        throw new ApiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
    }

    @Override
    public Comment updateComment(Long twinsId, Long id, CommentRequest commentRequest, UserPrincipal currentUser) {
        Twins twins = (Twins) getResourceById(twinsId, TWINS, twinsId);
        Comment comment = (Comment) getResourceById(id, COMMENT, id);

        assert comment != null;
        if (!comment.getTwins().getId().equals(twins.getId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
        }
        if(comment.getUser().getId().equals(currentUser.getId())
            || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            comment.setBody(commentRequest.getBody());
            return commentRepository.save(comment);
        }
        throw new ApiException(HttpStatus.UNAUTHORIZED, NO_PERMISSION_TO + "update this comment");
    }
    @Override
    public ApiResponse deleteComment(Long twinsId, Long id, UserPrincipal currentUser) {
        Twins twins = (Twins) getResourceById(twinsId, TWINS, twinsId);
        Comment comment = (Comment) getResourceById(id, COMMENT, id);

        assert comment != null;
        if(!comment.getTwins().getId().equals(twins.getId())){ return new ApiResponse(Boolean.FALSE, COMMENT_DOES_NOT_BELONG_TO_POST); }
        if(comment.getUser().getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            return new ApiResponse(Boolean.TRUE, SUCCESS_DELETE + "comment");
        }
        throw new ApiException(HttpStatus.UNAUTHORIZED, NO_PERMISSION_TO + "delete this comment");
    }
}
