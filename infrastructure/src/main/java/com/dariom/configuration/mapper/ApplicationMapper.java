package com.dariom.configuration.mapper;

import com.dariom.domain.model.Application;
import com.dariom.persistence.entities.ApplicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface ApplicationMapper {

    Application toApplication(ApplicationEntity applicationEntity);
}
