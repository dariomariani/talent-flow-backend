package com.dariom.controller;

import com.dariom.dto.ApiResponse;
import com.dariom.dto.AuthDto;
import com.dariom.dto.LoginDto;
import com.dariom.service.AuthenticationService;
import com.dariom.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ApiResponse<AuthDto> login(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = authenticationService.authenticate(loginDto);

        return ApiResponse.success(AuthDto.builder()
                .token(tokenService.generateToken(userDetails))
                .build());
    }

}
