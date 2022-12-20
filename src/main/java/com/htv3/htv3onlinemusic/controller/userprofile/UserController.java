package com.htv3.htv3onlinemusic.controller.userprofile;

import com.htv3.htv3onlinemusic.model.entity.User;
import com.htv3.htv3onlinemusic.model.dto.CustomMess;
import com.htv3.htv3onlinemusic.model.dto.RePass;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder encoder;

//    @GetMapping("/")
//    public ResponseEntity<Iterable<User>> findProfileUser(){
//        Iterable<User> users = userService.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> user1 = userService.findById(id);
        if (!user1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(user1.get().getId());
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody RePass rePassword) {

        User user = userService.findById(id).get();

//        if (!encoder.matches(rePassword.getCurrentPassword(), user.getPassword())) {
        if (!Objects.equals(rePassword.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new CustomMess("Mật khẩu cũ không khớp!"));
        }
//        user.setPassword(encoder.encode(rePassword.getNewPassword()));
        user.setPassword(rePassword.getNewPassword());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findByID(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get() , HttpStatus.OK);
    }
}
