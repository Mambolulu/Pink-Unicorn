package com.example.jwt.domain.product;

public class PurchaseResult {
    private final double totalPrice;
    private final int earnedSeeds;

    public PurchaseResult(double totalPrice, int earnedSeeds) {
        this.totalPrice = totalPrice;
        this.earnedSeeds = earnedSeeds;
    }

    // Getter für totalPrice und earnedSeeds
    public double getTotalPrice() {
        return totalPrice;
    }

    public int getEarnedSeeds() {
        return earnedSeeds;
    }
}
