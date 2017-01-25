package com.antonchar.userservice.repositories;

import com.antonchar.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name);
}
