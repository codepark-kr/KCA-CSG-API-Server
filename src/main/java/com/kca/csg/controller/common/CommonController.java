package com.kca.csg.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CommonController {

    @RequestMapping("/")
    public String goIndex(){ return "index";  }

    @RequestMapping("/console")
    public String goConsole(){ return "console";  }

}
