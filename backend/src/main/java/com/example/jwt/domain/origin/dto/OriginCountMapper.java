package com.example.jwt.domain.origin.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.origin.OriginCount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OriginCountMapper extends ExtendedMapper<OriginCount, OriginCountDTO> {
}
