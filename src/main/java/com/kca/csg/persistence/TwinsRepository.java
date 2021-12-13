package com.kca.csg.persistence;

import com.kca.csg.model.TwinsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TwinsRepository extends JpaRepository<TwinsEntity, String> {
    List<TwinsEntity> findById(Long id);
}