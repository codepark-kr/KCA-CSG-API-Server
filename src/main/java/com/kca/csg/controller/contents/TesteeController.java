package com.kca.csg.controller.contents;

import com.kca.csg.entity.Testee;
import com.kca.csg.repository.TesteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/testee")
public class TesteeController {

    @Autowired
    private TesteeRepository testeeRepository;

    @GetMapping("/all")
     public List<Testee> getAllTestees(){
        return testeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Testee> getTesteeById(@PathVariable(value="id") Long id,
                                                @Valid @RequestBody Testee testee) throws ResourceNotFoundException{
        Testee specifiedTestee = specificTesteeById(id);

        specifiedTestee.setTestTitle(testee.getTestTitle());
        specifiedTestee.setTestDescription(testee.getTestDescription());
        specifiedTestee.setTestDate(testee.getTestDate());

        final Testee resultTestee = testeeRepository.save(specifiedTestee);
        return ResponseEntity.ok(resultTestee);
    }

    public Testee specificTesteeById(Long id) throws ResourceNotFoundException {
        Testee returnTestee = testeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Couldn't find the target with this id :" + id));

        return returnTestee;
    }
}
