package com.dariom.domain.service;

import com.dariom.domain.model.User;

public interface UserDomainService {

    User getUserByEmail(String email);

}
