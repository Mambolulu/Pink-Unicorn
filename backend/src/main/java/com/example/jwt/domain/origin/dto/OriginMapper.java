package com.example.jwt.domain.origin.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.origin.Origin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OriginMapper extends ExtendedMapper<Origin, OriginDTO> {
}
