package com.kca.csg.repository;

import com.kca.csg.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTwinsId(Long twinsId, Pageable pageable);
}
