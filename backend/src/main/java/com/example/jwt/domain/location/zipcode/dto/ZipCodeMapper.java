package com.example.jwt.domain.location.zipcode.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.location.zipcode.ZipCode;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZipCodeMapper extends ExtendedMapper<ZipCode, ZipCodeDTO> {
}
