package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findAppUserByEmail(String email);
}
