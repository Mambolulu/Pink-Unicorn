package com.example.jwt.domain.product.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDTO extends ExtendedDTO {

    private String variety;
    private Category category;
    private Origin origin;
    private BigDecimal purchasePricePer100g;
    private BigDecimal sellingPricePer100g;
    private LocalDate harvestDate;
    private int stockInGram;

    public ProductDTO(UUID id, String variety, Category category, Origin origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stockInGram) {
        super(id);
        this.variety = variety;
        this.category = category;
        this.origin = origin;
        this.purchasePricePer100g = purchasePricePer100g;
        this.sellingPricePer100g = sellingPricePer100g;
        this.harvestDate = harvestDate;
        this.stockInGram = stockInGram;
    }

    public String getVariety() {
        return variety;
    }

    public ProductDTO setVariety(String variety) {
        this.variety = variety;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ProductDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Origin getOrigin() {
        return origin;
    }

    public ProductDTO setOrigin(Origin origin) {
        this.origin = origin;
        return this;
    }

    public BigDecimal getPurchasePricePer100g() {
        return purchasePricePer100g;
    }

    public ProductDTO setPurchasePricePer100g(BigDecimal purchasePricePer100g) {
        this.purchasePricePer100g = purchasePricePer100g;
        return this;
    }

    public BigDecimal getSellingPricePer100g() {
        return sellingPricePer100g;
    }

    public ProductDTO setSellingPricePer100g(BigDecimal sellingPricePer100g) {
        this.sellingPricePer100g = sellingPricePer100g;
        return this;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public ProductDTO setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
        return this;
    }

    public int getStockInGram() {
        return stockInGram;
    }

    public ProductDTO setStock(int stock) {
        this.stockInGram = stock;
        return this;
    }
}