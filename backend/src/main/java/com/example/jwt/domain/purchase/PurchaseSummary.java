package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedEntity;

import java.util.UUID;

public class PurchaseSummary extends ExtendedEntity {
    private String teaVariety;
    private int totalOrderedQuantity;
    private int totalCollectedSeeds;

    public PurchaseSummary(){ }

    public PurchaseSummary(UUID id, String teaVariety, int totalOrderedQuantity, int totalCollectedSeeds) {
        super(id);
        this.teaVariety = teaVariety;
        this.totalOrderedQuantity = totalOrderedQuantity;
        this.totalCollectedSeeds = totalCollectedSeeds;
    }

    public String getTeaVariety() {
        return teaVariety;
    }

    public PurchaseSummary setTeaVariety(String teaVariety) {
        this.teaVariety = teaVariety;
        return this;
    }

    public int getTotalOrderedQuantity() {
        return totalOrderedQuantity;
    }

    public PurchaseSummary setTotalOrderedQuantity(int totalOrderedQuantity) {
        this.totalOrderedQuantity = totalOrderedQuantity;
        return this;
    }

    public int getTotalCollectedSeeds() {
        return totalCollectedSeeds;
    }

    public PurchaseSummary setTotalCollectedSeeds(int totalCollectedSeeds) {
        this.totalCollectedSeeds = totalCollectedSeeds;
        return this;
    }
}
