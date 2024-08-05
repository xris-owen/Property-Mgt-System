package com.pacuss.property.mgt.serviceimp;

import com.pacuss.property.mgt.config.JwtProvider;
import com.pacuss.property.mgt.model.User;
import com.pacuss.property.mgt.repository.UserRepository;
import com.pacuss.property.mgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getUsernameFromJwt(jwt);
        return findUserByUsername(email);
    }

    @Override
    public User findUserByUsername(String email) throws Exception {
        User user = userRepository.findByUsername(email);

        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
