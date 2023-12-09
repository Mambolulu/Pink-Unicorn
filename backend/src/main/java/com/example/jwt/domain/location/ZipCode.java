package com.example.jwt.domain.location;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class ZipCode extends ExtendedEntity {

@Column(name = "place", nullable = false)
    private String place;

    @Column(name = "zip_code", nullable = false, unique = true)
    private int zipCode;

    public ZipCode() {
    }

    public ZipCode(UUID id, String place, int zipCode) {
        super(id);
        this.place = place;
        this.zipCode = zipCode;
    }

    public String getPlace() {
        return place;
    }

    public ZipCode setPlace(String place) {
        this.place = place;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ZipCode setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }
}
