package com.kca.csg.controller.contents.worktime;

import com.kca.csg.entity.UserEntity;
import com.kca.csg.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/all")
//    public List<UserEntity> getAllUsers(){
//        return postsRepository.findAll().toString();
//    }
    public String getAllUsers(){
        return postsRepository.findAll().toString();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "userId") Long userId,
        @Valid @RequestBody UserEntity userEntity) throws ResourceNotFoundException{
        UserEntity specifiedUser = specificUserById(userId);

         specifiedUser.setUserEmail(userEntity.getUserEmail());
         specifiedUser.setUserName(userEntity.getUserName());
         final UserEntity resultUserEntity = postsRepository.save(specifiedUser);

         return ResponseEntity.ok(resultUserEntity);
    }

    @DeleteMapping("/{userId}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "userId") Long userId){
        UserEntity specifiedUser = specificUserById(userId);
        postsRepository.delete(specifiedUser);

        Map<String, Boolean> result = new HashMap<>();
        result.put("The user has been deleted", Boolean.TRUE);
        return result;
    }

    public UserEntity specificUserById(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        UserEntity returnUser = postsRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find the user with this id : " + userId));

        return returnUser;
    }
}
