package com.example.jwt.domain.origin;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "origins")
public class Origin extends ExtendedEntity {

    private String country;

    public Origin() {}

    public Origin(UUID id, String country) {
        super(id);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Origin setCountry(String country) {
        this.country = country;
        return this;
    }
}
