package com.dariom.service;

import com.dariom.configuration.mapper.UserMapper;
import com.dariom.domain.model.User;
import com.dariom.persistence.entities.UserEntity;
import com.dariom.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserEntityService sut;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).displayName("John Doe").username("john.doe@example.com").build();
        userEntity = UserEntity.builder().id(1L).displayName("John Doe").username("john.doe@example.com").build();
    }

    @Test
    void testGetUserById_ShouldReturnUser_WhenUserExists() {
        when(userRepository.findByUsername("john.doe@example.com")).thenReturn(Optional.of(userEntity));
        when(userMapper.toUser(userEntity)).thenReturn(user);

        User result = sut.getUserByEmail("john.doe@example.com");

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getDisplayName(), result.getDisplayName());

        verify(userRepository, times(1)).findByUsername("john.doe@example.com");
        verify(userMapper, times(1)).toUser(userEntity);
    }

    @Test
    void testGetUserById_ShouldReturnNull_WhenUserDoesNotExist() {
        when(userRepository.findByUsername("john.doe@example.com")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> sut.getUserByEmail("john.doe@example.com"));

    }
}