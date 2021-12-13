package com.kca.csg.controller;

import com.kca.csg.dto.ResponseDTO;
import com.kca.csg.dto.TwinsDto;
import com.kca.csg.model.Twins;
import com.kca.csg.service.TwinsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/twins")
public class TwinsController {

    @Autowired
    TwinsService twinsService;

    @PostMapping("/create")
    public ResponseEntity<?> createTwins(@RequestBody TwinsDto dto){
        try{
            Twins entity = TwinsDto.toEntity(dto);
            log.info("request ? {}", dto.toString());
            entity.setId(dto.getId());
            entity.setKorTitle(dto.getKorTitle());
            entity.setKorContent(dto.getKorContent());
            entity.setEngTitle(dto.getEngTitle());
            entity.setEngContent(dto.getEngContent());
            entity.setEnrollDate(new Date());
            entity.setLastUpdate(new Date());

            log.info("review ? {}", entity.toString());
            Optional<Twins> entities = twinsService.create(entity);
            List<TwinsDto> dtos = entities.stream().map(TwinsDto::new).collect(Collectors.toList());
            ResponseDTO<TwinsDto> response = ResponseDTO.<TwinsDto>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TwinsDto> response = ResponseDTO.<TwinsDto>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}