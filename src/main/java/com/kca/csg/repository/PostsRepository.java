package com.kca.csg.repository;

import com.kca.csg.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<UserEntity, Long> {

}
