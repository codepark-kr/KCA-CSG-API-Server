package com.kca.csg.controller.contents;

import com.kca.csg.dto.ResponseDto;
import com.kca.csg.entity.Testee;
import com.kca.csg.service.TestService;
import com.kca.csg.service.TesteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/testee")
public class TesteeController {

    @Autowired
    private TestService service;

//    @GetMapping("/all")
//    public ResponseEntity<?> testTestee(){
//        Testee testee = service.testService();
//        List<Testee> result = new ArrayList<>();
//
//        result.add(testee);
//        ResponseDto<Testee> response = ResponseDto.<Testee>builder().data(result).status("OK").build();
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/create")
    public ResponseEntity<Testee> create(@RequestBody Testee testee){
        return ResponseEntity.ok().body(service.create(testee));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Testee> read(@PathVariable Long id){
        return ResponseEntity.ok().body(service.read(id).get());
    }

    @PutMapping("/update")
    public ResponseEntity<Testee> read(@RequestParam Long id, @RequestParam String title){
        return ResponseEntity.ok().body(service.update(id, title));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
