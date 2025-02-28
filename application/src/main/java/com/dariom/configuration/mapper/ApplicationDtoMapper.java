package com.dariom.configuration.mapper;

import com.dariom.domain.model.Application;
import com.dariom.domain.model.ApplicationStatus;
import com.dariom.dto.ApplicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApplicationDtoMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "toStatus")
    @Mapping(source = "user.displayName", target = "candidateDisplayName")
    ApplicationDto toApplicationDto(Application application);

    @Named("toStatus")
    static String toStatus(ApplicationStatus status) {
        if (status == null) return null;
        return status.name();
    }
}
