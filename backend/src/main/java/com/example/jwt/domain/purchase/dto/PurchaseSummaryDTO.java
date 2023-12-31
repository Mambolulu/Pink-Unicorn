package com.example.jwt.domain.purchase.dto;

import com.example.jwt.core.generic.ExtendedDTO;

public class PurchaseSummaryDTO extends ExtendedDTO {
    private String teaVariety;
    private Long totalOrderedQuantity;
    private Long totalCollectedSeeds;

    public PurchaseSummaryDTO(String teaVariety, Long totalOrderedQuantity, Long totalCollectedSeeds) {
        this.teaVariety = teaVariety;
        this.totalOrderedQuantity = totalOrderedQuantity;
        this.totalCollectedSeeds = totalCollectedSeeds;
    }

    public String getTeaVariety() {
        return teaVariety;
    }

    public PurchaseSummaryDTO setTeaVariety(String teaVariety) {
        this.teaVariety = teaVariety;
        return this;
    }

    public Long getTotalOrderedQuantity() {
        return totalOrderedQuantity;
    }

    public PurchaseSummaryDTO setTotalOrderedQuantity(Long totalOrderedQuantity) {
        this.totalOrderedQuantity = totalOrderedQuantity;
        return this;
    }

    public Long getTotalCollectedSeeds() {
        return totalCollectedSeeds;
    }

    public PurchaseSummaryDTO setTotalCollectedSeeds(Long totalCollectedSeeds) {
        this.totalCollectedSeeds = totalCollectedSeeds;
        return this;
    }
}
