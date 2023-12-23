package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "products")
public class Product extends ExtendedEntity {

  private String name;

  private String origin;

  private BigDecimal purchasePricePer100g;

  private BigDecimal sellingPricePer100g;

  private LocalDate harvestDate;

  private int stock;

  public Product() { }

  public Product(UUID id, String name, String origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stock) {
    super(id);
    this.name = name;
    this.origin = origin;
    this.purchasePricePer100g = purchasePricePer100g;
    this.sellingPricePer100g = sellingPricePer100g;
    this.harvestDate = harvestDate;
    this.stock = stock;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public String getOrigin() {
    return origin;
  }

  public Product setOrigin(String origin) {
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

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}