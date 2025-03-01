package com.dariom.controller;

import com.dariom.configuration.mapper.UserDtoMapper;
import com.dariom.domain.service.UserDomainService;
import com.dariom.dto.ApiResponse;
import com.dariom.dto.UserDto;
import com.dariom.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ApiResponse<UserDto> login(@RequestHeader("Authorization") String authorizationHeader) {
        final String token = authorizationHeader.substring(7);
        final String userName = tokenService.getUsernameFromToken(token);
        return ApiResponse.success(userDtoMapper.toUserDto(userDomainService.getUserByEmail(userName)));
    }
}
