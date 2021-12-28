package com.kca.csg.controller.api;

import com.kca.csg.model.Comment;
import com.kca.csg.payload.request.CommentRequest;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kca.csg.util.Constants.*;

@RestController
@RequestMapping("/api/twins/{twinsId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Comment>> getAllComments(@PathVariable(name = "twinsId") Long twinsId,
             @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer page,
             @RequestParam(name = "size", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer size) {
        return new ResponseEntity<>(commentService.getAllComments(twinsId, page, size), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentRequest commentRequest,
             @PathVariable(name = "twisnId") Long twinsId, @CurrentUser UserPrincipal currentUser){
        return new ResponseEntity<>(commentService.addComment(commentRequest, twinsId, currentUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable(name = "twinsId") Long twinsId, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.getComment(twinsId, id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment(@PathVariable(name = "twinsId") Long twinsId, @PathVariable(name = "id") Long id,
                                                 @Valid @RequestBody CommentRequest commentRequest, @CurrentUser UserPrincipal currentUser){
        return new ResponseEntity<>(commentService.updateComment(twinsId, id, commentRequest, currentUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "twinsId") Long twinsId, @PathVariable(name = "id") Long id,
                                                     @CurrentUser UserPrincipal currentUser){
        ApiResponse response = commentService.deleteComment(twinsId, id, currentUser);
        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, status);
    }
}