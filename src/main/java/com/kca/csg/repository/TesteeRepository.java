package com.kca.csg.repository;

import com.kca.csg.entity.Testee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteeRepository extends JpaRepository<Testee, Long> {
}
