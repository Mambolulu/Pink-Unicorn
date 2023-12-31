package com.example.jwt.domain.location.zipcode;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.location.place.Place;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "zip_code")
public class ZipCode extends ExtendedEntity {

    @Column(name = "zip_code", nullable = false)
    private int zipCode;

    @ManyToOne
    @JoinColumn(name = "place", referencedColumnName = "id")
    private Place place;
    public ZipCode() {
    }

    public ZipCode(UUID id, int zipCode, Place place) {
        super(id);
        this.zipCode = zipCode;
        this.place = place;
    }

    public int getZipCode() {
        return zipCode;
    }

    public ZipCode setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Place getPlace() {
        return place;
    }

    public ZipCode setPlace(Place place) {
        this.place = place;
        return this;
    }
}
