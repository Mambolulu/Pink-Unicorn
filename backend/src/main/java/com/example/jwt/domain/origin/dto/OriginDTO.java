package com.example.jwt.domain.origin.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import java.util.UUID;

public class OriginDTO extends ExtendedDTO {

    private String country;

    public OriginDTO(UUID id, String country) {
        super(id);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public OriginDTO setCountry(String country) {
        this.country = country;
        return this;
    }
}
