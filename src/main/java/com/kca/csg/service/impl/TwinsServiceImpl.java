package com.kca.csg.service.impl;

import com.kca.csg.repository.CategoryRepository;
import com.kca.csg.repository.TagRepository;
import com.kca.csg.repository.TwinsRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.service.TwinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwinsServiceImpl implements TwinsService {

    @Autowired
    private TwinsRepository twinsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;



}
