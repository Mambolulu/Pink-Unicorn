package com.example.jwt.domain.rank;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Rank extends ExtendedEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "discount")
    private double discount;

    @Column(name = "needed_seeds", nullable = false)
    private int neededSeeds;

    public Rank() {
    }

    public Rank(UUID id, String name, double discount, int neededSeeds) {
        super(id);
        this.name = name;
        this.discount = discount;
        this.neededSeeds = neededSeeds;
    }

    public String getName() {
        return name;
    }

    public Rank setName(String name) {
        this.name = name;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public Rank setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public int getNeededSeeds() {
        return neededSeeds;
    }

    public Rank setNeededSeeds(int neededSeeds) {
        this.neededSeeds = neededSeeds;
        return this;
    }
}
