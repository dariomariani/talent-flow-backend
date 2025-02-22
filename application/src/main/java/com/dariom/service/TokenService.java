package com.dariom.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(UserDetails userDetails);

    boolean verifyToken(String token);

    String getUsernameFromToken(String token);
}
