package com.htv3.htv3onlinemusic.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NotNull
    @Size(min = 3, max = 28)
    private String username;

    @NotNull
    @Size(min = 6, max = 8)
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
