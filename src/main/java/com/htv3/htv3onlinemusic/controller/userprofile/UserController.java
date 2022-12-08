package com.htv3.htv3onlinemusic.controller.userprofile;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.User;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

//    @GetMapping("/")
//    public ResponseEntity<Iterable<User>> findProfileUser(){
//        Iterable<User> users = userService.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> user1 = userService.findById(id);
        if (!user1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(user1.get().getId());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findByID(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get() , HttpStatus.NOT_FOUND);
    }
}
