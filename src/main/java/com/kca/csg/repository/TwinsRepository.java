package com.kca.csg.repository;

import com.kca.csg.model.Twins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwinsRepository extends JpaRepository<Twins, Long> {
}