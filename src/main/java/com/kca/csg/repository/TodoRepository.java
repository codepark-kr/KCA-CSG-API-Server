package com.kca.csg.repository;

import com.kca.csg.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String>{
    List<Todo> findByUserId(String userId);
}
