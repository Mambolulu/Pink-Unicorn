package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends ExtendedEntity {

  @Column(name = "variety")
  private String variety;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "origin_id")
  private Origin origin;

  private BigDecimal purchasePricePer100g;

  private BigDecimal sellingPricePer100g;

  private LocalDate harvestDate;

  @Column(name = "stock_in_gram")
  private int stockInGram;

  public Product() { }

  public Product(UUID id, String variety, Category category, Origin origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stockInGram) {
    super(id);
    this.variety = variety;
    this.category = category;
    this.origin = origin;
    this.purchasePricePer100g = purchasePricePer100g;
    this.sellingPricePer100g = sellingPricePer100g;
    this.harvestDate = harvestDate;
    this.stockInGram = stockInGram;
  }

  public Product(String variety, Category category, Origin origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stock) {
    this.variety = variety;
    this.category = category;
    this.origin = origin;
    this.purchasePricePer100g = purchasePricePer100g;
    this.sellingPricePer100g = sellingPricePer100g;
    this.harvestDate = harvestDate;
    this.stock = stock;
  }

  public String getVariety() {
    return variety;
  }

  public Product setVariety(String variety) {
    this.variety = variety;
    return this;
  }

  public Category getCategory() {
    return category;
  }

  public Product setCategory(Category category) {
    this.category = category;
    return this;
  }

  public Origin getOrigin() {
    return origin;
  }

  public Product setOrigin(Origin origin) {
    this.origin = origin;
    return this;
  }

  public BigDecimal getPurchasePricePer100g() {
    return purchasePricePer100g;
  }

  public Product setPurchasePricePer100g(BigDecimal purchasePricePer100g) {
    this.purchasePricePer100g = purchasePricePer100g;
    return this;
  }

  public BigDecimal getSellingPricePer100g() {
    return sellingPricePer100g;
  }

  public Product setSellingPricePer100g(BigDecimal sellingPricePer100g) {
    this.sellingPricePer100g = sellingPricePer100g;
    return this;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public Product setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
    return this;
  }

  public int getStockInGram() {
    return stockInGram;
  }

  public Product setStockInGram(int stock) {
    this.stockInGram = stock;
    return this;
  }
}