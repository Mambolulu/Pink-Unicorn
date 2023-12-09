package com.example.jwt.domain.location.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class ZipCodeDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1000, max = 9999)
    private int zipCode;

    @NotNull
    private String place;

    public ZipCodeDTO(UUID id, int zipcode, String place) {
        super(id);
        this.zipCode = zipcode;
        this.place = place;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ZipCodeDTO setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public ZipCodeDTO setPlace(String place) {
        this.place = place;
        return this;
    }
}
