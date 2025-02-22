package com.dariom.security.service;

import com.dariom.dto.LoginDto;
import com.dariom.dto.RegisterDto;
import com.dariom.persistence.entities.UserEntity;
import com.dariom.persistence.repositories.UserRepository;
import com.dariom.application.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasicAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public BasicAuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterDto registerDto) {
        UserEntity user = UserEntity.builder()
                            .username(registerDto.getEmail())
                            .displayName(registerDto.getFullName())
                            .password(registerDto.getPassword())
                            .build();
        userRepository.save(user);
    }

    @Override
    public UserDetails authenticate(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            return userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
        }
        catch (AuthenticationException e) {
            log.error("Unable to authenticate user: {} -> {}", loginDto.getUsername(), e.getMessage());
            throw new BadCredentialsException("Invalid username or password.");
        }
    }
}
