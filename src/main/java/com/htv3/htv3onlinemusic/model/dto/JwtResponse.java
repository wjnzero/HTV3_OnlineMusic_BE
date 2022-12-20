package com.htv3.htv3onlinemusic.model.dto;

import com.htv3.htv3onlinemusic.model.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Data
@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String username;
    private String token;
    private Set<Role> roleSet;

    public JwtResponse(Long id, String username, String token, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roleSet = roles;
    }
}
