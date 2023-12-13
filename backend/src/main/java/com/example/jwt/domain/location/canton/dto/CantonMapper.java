package com.example.jwt.domain.location.canton.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.location.canton.Canton;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CantonMapper extends ExtendedMapper<Canton, CantonDTO> {
}
