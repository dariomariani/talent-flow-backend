package com.dariom.configuration.mapper;

import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {ApplicationDtoMapper.class})
public interface JobDtoMapper {
}
