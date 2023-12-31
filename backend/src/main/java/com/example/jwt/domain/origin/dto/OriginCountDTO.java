package com.example.jwt.domain.origin.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import java.util.UUID;

public class OriginCountDTO extends ExtendedDTO {

    private String country;
    private int orderCount;

    public OriginCountDTO(UUID id, String country, int orderCount) {
        super(id);
        this.country = country;
        this.orderCount = orderCount;
    }

    public String getCountry() {
        return country;
    }

    public OriginCountDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public OriginCountDTO setOrderCount(int orderCount) {
        this.orderCount = orderCount;
        return this;
    }
}
