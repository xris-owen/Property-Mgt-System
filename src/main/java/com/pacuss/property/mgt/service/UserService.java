package com.pacuss.property.mgt.service;

import com.pacuss.property.mgt.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByUsername(String username) throws Exception;
}
