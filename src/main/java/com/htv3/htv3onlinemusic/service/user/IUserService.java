package com.htv3.htv3onlinemusic.service.user;

import com.htv3.htv3onlinemusic.model.entity.User;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends GenericService<User>, UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User getUserByUsername(String username);

    User findAppUserByEmail(String email);
}
