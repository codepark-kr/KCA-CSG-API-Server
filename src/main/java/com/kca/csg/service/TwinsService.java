package com.kca.csg.service;

import com.kca.csg.model.TwinsEntity;
import com.kca.csg.persistence.TwinsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TwinsService {

    @Autowired
    private TwinsRepository twinsRepository;

    public List<TwinsEntity> create(final TwinsEntity entity){
        validate(entity);
        twinsRepository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
        twinsRepository.flush();
        log.debug("result ? {}", twinsRepository.findById(entity.getId()));
        return twinsRepository.findById(entity.getId());
    }

    private void validate(final TwinsEntity entity){
        if(entity == null){ log.warn("Entity cannot be null."); throw new RuntimeException("Entity cannot be null"); }
//        if(entity.getId() == null){ log.warn("Unknown identifier"); throw new RuntimeException("Unknown identifier"); }
    }

//    public List<Twins> retrieve(final Long id){ return twinsRepository.findById(id); }

//    public List<Twins> update(final Twins entity){
//        validate(entity);
//        final List<Twins> original = twinsRepository.findById(entity.getId());
//
//        original.ifPresent(twin -> {
//            twin.setKorTitle(entity.getKorTitle());
//            twin.setKorContent(entity.getKorContent());
//            twin.setEngTitle(entity.getEngTitle());
//            twin.setEngContent(entity.getEngContent());
//        });
//        return retrieve(entity.getId());
//    }

//    public List<Twins> delete(final Twins entity){
//        validate(entity);
//
//        try{}catch(Exception e){
//            log.error("error deleting entity", entity.getId(), e);
//            throw new RuntimeException("error deleting entity" + entity.getId());
//        }
//        return retrieve(entity.getId());
//    }
}
