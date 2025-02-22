package com.dariom.service;

import com.dariom.dto.LoginDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticate(LoginDto loginDto);

}
