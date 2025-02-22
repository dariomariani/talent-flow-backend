package com.dariom.application;

import com.dariom.dto.LoginDto;
import com.dariom.dto.RegisterDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    void register(RegisterDto registerDto);

    UserDetails authenticate(LoginDto loginDto);

}
