package com.kca.csg.controller;

import com.kca.csg.dto.ResponseDTO;
import com.kca.csg.dto.TwinsDto;
import com.kca.csg.model.TwinsEntity;
import com.kca.csg.service.TwinsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/twins")
public class TwinsController {

    @Autowired
    TwinsService twinsService;

    @PostMapping("/create")
    public ResponseEntity<?> createTwins(@RequestBody TwinsDto dto){
        try{
            TwinsEntity entity = TwinsDto.toEntity(dto);
            entity.setId(dto.getId());
            entity.setKorTitle(dto.getKorTitle());
            entity.setKorContent(dto.getKorContent());
            entity.setEngTitle(dto.getEngTitle());
            entity.setEngContent(dto.getEngContent());
            entity.setEnrollDate(new Timestamp(new Date().getTime()));
            entity.setLastUpdate(new Timestamp(new Date().getTime()));

            List<TwinsEntity> entities = twinsService.create(entity);
            List<TwinsDto> dtos = new ArrayList<>();
            dtos.add(dto);
//                    entities.stream().map(TwinsDto::new).collect(Collectors.toList());
            ResponseDTO<TwinsDto> response =
                    ResponseDTO.<TwinsDto>builder().status("OK").data(dtos).build();

            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TwinsDto> response = ResponseDTO.<TwinsDto>builder().status(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}