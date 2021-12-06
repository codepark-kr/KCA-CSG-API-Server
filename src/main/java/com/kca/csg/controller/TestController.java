package com.kca.csg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1")
public class TestController {

    @GetMapping("/now")
    public @ResponseBody String now(){
        return "curr - system time is [ "+ new Date()+ " ]";
    }
}
