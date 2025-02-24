package com.dariom.controller;

import com.dariom.configuration.mapper.UserDtoMapper;
import com.dariom.domain.service.UserDomainService;
import com.dariom.dto.ApiResponse;
import com.dariom.dto.UserDto;
import com.dariom.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @PostMapping("/me")
    public ApiResponse<UserDto> login(@RequestHeader("Authorization") String authorizationHeader) {
        final String token = authorizationHeader.substring(7);
        final String userName = tokenService.getUsernameFromToken(token);
        return ApiResponse.success(userDtoMapper.toUserDto(userDomainService.getUserByEmail(userName)));
    }
}
