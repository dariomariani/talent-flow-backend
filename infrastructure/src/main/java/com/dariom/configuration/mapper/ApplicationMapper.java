package com.dariom.configuration.mapper;

import com.dariom.domain.model.Application;
import com.dariom.domain.model.ApplicationStatus;
import com.dariom.persistence.entities.ApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface ApplicationMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "toStatus")
    Application toApplication(ApplicationEntity applicationEntity);

    @Named("toStatus")
    static ApplicationStatus toStatus(String entityStatus) {
        if (entityStatus == null || entityStatus.isEmpty()) return null;
        try {
            return ApplicationStatus.fromDisplayName(entityStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status name: " + entityStatus);
        }
    }
}
