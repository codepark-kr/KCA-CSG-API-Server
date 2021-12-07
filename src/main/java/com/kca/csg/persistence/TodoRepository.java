package com.kca.csg.persistence;

import com.kca.csg.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
    List<TodoEntity> findByUserId(String userId);
}
