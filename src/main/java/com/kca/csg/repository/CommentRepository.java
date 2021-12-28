package com.kca.csg.repository;

import com.kca.csg.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTwinsId(Long twinsId, Pageable pageable);
}
