package com.kca.csg.repository;

import com.kca.csg.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    Page<Todo> findByCreatedBy(Long userId, Pageable pageable);
}
