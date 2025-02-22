package com.dariom.controller;

import com.dariom.dto.ApiResponse;
import com.dariom.dto.AuthDto;
import com.dariom.dto.LoginDto;
import com.dariom.application.AuthenticationService;
import com.dariom.application.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @SecurityRequirement(name = "BearerAuth")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String authHeader) {
        String userName = tokenService.getUsernameFromToken(authHeader.substring("Bearer ".length()));
        log.info("Logged out user: {}", userName);
        return ApiResponse.success("Logged out successfully.");
    }

}
