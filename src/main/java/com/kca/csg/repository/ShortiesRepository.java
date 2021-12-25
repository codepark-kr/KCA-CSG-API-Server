package com.kca.csg.repository;

import com.kca.csg.model.Shorties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortiesRepository extends JpaRepository<Shorties, Long> {

    Page<Shorties> findByCreatedBy(Long userId, Pageable pageable);
    Long countByCreatedBy(Long userId);
}
