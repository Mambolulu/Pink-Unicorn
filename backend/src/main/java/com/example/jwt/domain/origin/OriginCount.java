package com.example.jwt.domain.origin;

import com.example.jwt.core.generic.ExtendedEntity;

import java.util.UUID;

public class OriginCount extends ExtendedEntity {

    private String country;
    private int orderCount;

    public OriginCount() {}

    public OriginCount(UUID id, String country, int orderCount) {
        super(id);
        this.country = country;
        this.orderCount = orderCount;
    }

    public String getCountry() {
        return country;
    }

    public OriginCount setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public OriginCount setOrderCount(int orderCount) {
        this.orderCount = orderCount;
        return this;
    }
}
