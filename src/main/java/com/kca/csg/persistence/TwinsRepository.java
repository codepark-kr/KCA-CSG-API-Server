package com.kca.csg.persistence;

import com.kca.csg.model.Twins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TwinsRepository extends JpaRepository<Twins, String> {
    Optional<Twins> findById(String id);
}