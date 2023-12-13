package com.example.jwt.domain.location.canton;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Canton extends ExtendedEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    public Canton() {
    }

    public Canton(UUID id, String name, String abbreviation) {
        super(id);
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public Canton setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Canton setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }
}
