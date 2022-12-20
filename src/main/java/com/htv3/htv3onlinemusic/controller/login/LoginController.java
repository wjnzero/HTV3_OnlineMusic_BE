package com.htv3.htv3onlinemusic.controller.login;

import com.htv3.htv3onlinemusic.model.entity.Role;
import com.htv3.htv3onlinemusic.model.entity.User;
import com.htv3.htv3onlinemusic.model.dto.JwtResponse;
import com.htv3.htv3onlinemusic.model.dto.LoginForm;
import com.htv3.htv3onlinemusic.service.jwt.JwtService;
import com.htv3.htv3onlinemusic.service.role.IRoleService;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm loginForm) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token mới
            String token = jwtService.createToken(authentication);
            User user1 = userService.getUserByUsername(loginForm.getUsername());
            JwtResponse jwtResponse = new JwtResponse(user1.getId(), user1.getUsername(), token, user1.getRoleSet());
            return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        if (userService.getUserByUsername(user.getUsername()) == null) {
            Set<Role> roles = new HashSet<>();
            //2 id member
            roles.add(roleService.findById(2L).get());
            user.setRoleSet(roles);
            user.setAvatar("https://cdn.britannica.com/42/142842-050-83690FAB/soldier-bunker-Viet-Cong-Vietnam-War.jpg");
            User appUser = userService.save(user);
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
