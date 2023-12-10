package com.example.jwt.domain.location.zipcode.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.location.place.Place;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ZipCodeDTO extends ExtendedDTO {

    @NotNull
    @Min(1000)
    @Max(9658)
    private int zipCode;

    @Valid
    private Place place;

    public ZipCodeDTO() {
    }

    public ZipCodeDTO(UUID id, int zipCode, Place place) {
        super(id);
        this.zipCode = zipCode;
        this.place = place;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ZipCodeDTO setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Place getPlace() {
        return place;
    }

    public ZipCodeDTO setPlace(Place place) {
        this.place = place;
        return this;
    }
}
