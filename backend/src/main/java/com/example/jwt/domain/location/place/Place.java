package com.example.jwt.domain.location.place;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.location.canton.Canton;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Place extends ExtendedEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "canton", referencedColumnName = "id")
    private Canton canton;

    public Place() {
    }

    public Place(UUID id, String name, Canton canton) {
        super(id);
        this.name = name;
        this.canton = canton;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public Canton getCanton() {
        return canton;
    }

    public Place setCanton(Canton canton) {
        this.canton = canton;
        return this;
    }
}
