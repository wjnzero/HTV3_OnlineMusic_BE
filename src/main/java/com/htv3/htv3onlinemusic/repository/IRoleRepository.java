package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
}
