package com.kca.csg.service;

import com.kca.csg.model.Twins;
import com.kca.csg.payload.PagedResponse;
import com.kca.csg.repository.TwinsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TwinsService {

    PagedResponse<Twins> getAllTwins(int page, int size);


}
