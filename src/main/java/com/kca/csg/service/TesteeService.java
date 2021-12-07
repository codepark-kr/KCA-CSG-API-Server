package com.kca.csg.service;

import com.kca.csg.entity.Testee;
import com.kca.csg.repository.TesteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteeService {

    @Autowired
    private TesteeRepository testeeRepository;

    public Testee testService(){
        Testee testee = Testee.builder().testTitle("TEST TITLE @SERVICE").testDescription("TEST DESCRIPTION @SERVICE").build();
        testeeRepository.save(testee);
        Testee savedTestee = testeeRepository.findById(testee.getId()).get();

        return savedTestee;
    }
}
