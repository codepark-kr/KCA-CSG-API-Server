package com.kca.csg.service;

import com.kca.csg.entity.Testee;
import com.kca.csg.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    private TestRepository repository;

    public TestService(TestRepository repository) {
        this.repository = repository;
    }

    public Testee create(Testee testee){ return repository.save(testee); }

    public Optional<Testee> read(Long id){ return repository.findById(id); }

    public Testee update(Long id, String testTitle){
        Testee testee = read(id).get();
        testee.setTestTitle(testTitle);
        return repository.save(testee);
    }

    public void delete(Long id){ repository.deleteById(id); }
}
