package com.kca.csg.service.impl;

import com.kca.csg.repository.CategoryRepository;
import com.kca.csg.repository.TagRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.service.TwinsService;
import org.springframework.stereotype.Service;

@Service
public class TwinsServiceImpl implements TwinsService {

    private final TwinsRepository twinsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    public TwinsServiceImpl(TwinsRepository twinsRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.twinsRepository = twinsRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }
}
