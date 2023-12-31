package com.example.jwt.domain.purchase.dto;

import com.example.jwt.core.generic.ExtendedMapper;
import com.example.jwt.domain.purchase.PurchaseSummary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseSummaryMapper extends ExtendedMapper<PurchaseSummary, PurchaseSummaryDTO> {
}
