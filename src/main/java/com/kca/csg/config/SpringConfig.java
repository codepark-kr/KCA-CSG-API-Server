package com.kca.csg.config;

import com.kca.csg.repository.TestRepository;
import com.kca.csg.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SpringConfig {
    private final TestRepository repository;

    @Autowired
    public SpringConfig(TestRepository repository) {
        this.repository = repository;
    }

    @Bean
    public TestService service(){
        return new TestService(repository);
    }

}
