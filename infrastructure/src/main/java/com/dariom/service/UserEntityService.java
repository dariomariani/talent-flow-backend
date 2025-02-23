package com.dariom.service;

import com.dariom.configuration.UserMapper;
import com.dariom.domain.model.User;
import com.dariom.domain.service.UserDomainService;
import com.dariom.persistence.entities.UserEntity;
import com.dariom.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService implements UserDomainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByUsername(email).orElseThrow();
        return userMapper.toUser(userEntity);
    }
}
