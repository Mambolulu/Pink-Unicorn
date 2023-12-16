package com.example.jwt.domain.user;

public enum CustomerRank {
    BRONZE(0, 0),
    SILVER(0.04, 20),
    GOLD(0.07, 60),
    PLATINUM(0.09, 140),
    DIAMOND(0.11, 300);

    private final double discount;
    private final int seedsRequired;

    CustomerRank(double discount, int seedsRequired) {
        this.discount = discount;
        this.seedsRequired = seedsRequired;
    }

    public double getDiscount() {
        return discount;
    }

    public int getSeedsRequired() {
        return seedsRequired;
    }

    public static CustomerRank determineRank(int seeds) {
        if (seeds >= DIAMOND.seedsRequired) {
            return DIAMOND;
        } else if (seeds >= PLATINUM.seedsRequired) {
            return PLATINUM;
        } else if (seeds >= GOLD.seedsRequired) {
            return GOLD;
        } else if (seeds >= SILVER.seedsRequired) {
            return SILVER;
        } else {
            return BRONZE;
        }
    }
}
