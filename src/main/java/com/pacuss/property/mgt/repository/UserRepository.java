package com.pacuss.property.mgt.repository;

import com.pacuss.property.mgt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
