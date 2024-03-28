package com.AvA.repository;

import com.AvA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    // can be changed what want to be find by
    public User findByEmail(String email);

}
