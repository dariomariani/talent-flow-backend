package com.dariom.configuration.mapper;

import com.dariom.domain.model.User;
import com.dariom.dto.UserDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserDtoMapper {

    UserDto toUserDto(User user);
}
