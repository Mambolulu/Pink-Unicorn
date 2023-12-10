package com.example.jwt.domain.location.place.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.location.place.Place;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaceMapper extends ExtendedMapper<Place, PlaceDTO> {

}
