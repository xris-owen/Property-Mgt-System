package com.pacuss.property.mgt.controller;

import com.pacuss.property.mgt.config.JwtProvider;
import com.pacuss.property.mgt.model.User;
import com.pacuss.property.mgt.repository.UserRepository;
import com.pacuss.property.mgt.request.LoginRequest;
import com.pacuss.property.mgt.response.AuthResponse;
import com.pacuss.property.mgt.serviceimp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private CustomUserDetailsService customUserDetailsService;
    @Autowired private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User doesUsernameExist = userRepository.findByUsername(user.getEmail());

        if(doesUsernameExist != null){
            throw new Exception("Username already exist.");
        }

        User createdUser = new User();
        createdUser.setFullName(user.getFullName());
        createdUser.setEmail(user.getEmail());
        createdUser.setUsername(user.getUsername());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // Create user
        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUsername(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> sigInHandler(@RequestBody LoginRequest req){
        String username = req.getUsername();
        String password = req.getPassword();

        Authentication authentication = authenticate(username, password);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Signin successful");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if(userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword()) ) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }


}
