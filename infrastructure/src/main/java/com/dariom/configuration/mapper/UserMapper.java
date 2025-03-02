package com.dariom.configuration.mapper;

import com.dariom.domain.model.Role;
import com.dariom.domain.model.User;
import com.dariom.persistence.entities.RoleEntity;
import com.dariom.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface  UserMapper {

    @Mapping(source = "role", target = "role", qualifiedByName = "toRole")
    User toUser(UserEntity userEntity);

    @Named("toRole")
    static Role toRole(RoleEntity roleEntity) {
        if (roleEntity == null) return null;
        final String roleName = roleEntity.getName();
        try {
            return Role.valueOf(Role.class, roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role name: " + roleName);
        }
    }

}
