package com.kca.csg.repository;

import com.kca.csg.model.Tag;
import com.kca.csg.model.Twins;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwinsRepository extends JpaRepository<Twins, Long> {
    Page<Twins> findByCreatedBy(Long userId, Pageable pageable);

    Page<Twins> findByCategory(Long categoryId, Pageable pageable);

    Page<Twins> findByTags(List<Tag> tags, Pageable pageable);

    Long countByCreatedBy(Long userId);
}
