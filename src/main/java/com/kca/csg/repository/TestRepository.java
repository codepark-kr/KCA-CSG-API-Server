package com.kca.csg.repository;

import com.kca.csg.entity.Testee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Testee, Long> {

    @Override
    Optional<Testee> findById(Long id);

    @Override
    Testee save(Testee testee);

    @Override
    void deleteById(Long id);
}
