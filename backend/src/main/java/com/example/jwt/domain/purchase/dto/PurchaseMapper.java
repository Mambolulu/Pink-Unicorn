package com.example.jwt.domain.purchase.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.purchase.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseMapper extends ExtendedMapper<Purchase, PurchaseDTO> {
}
