package com.example.jwt.domain.rank.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import java.util.UUID;

public class RankDTO extends ExtendedDTO {

    private String name;
    private double discount;
    private int neededSeeds;

    public RankDTO() {
    }

    public RankDTO(UUID id, String name, double discount, int neededSeeds) {
        super(id);
        this.name = name;
        this.discount = discount;
        this.neededSeeds = neededSeeds;
    }

    public String getName() {
        return name;
    }

    public RankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public RankDTO setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public int getNeededSeeds() {
        return neededSeeds;
    }

    public RankDTO setNeededSeeds(int neededSeeds) {
        this.neededSeeds = neededSeeds;
        return this;
    }
}
