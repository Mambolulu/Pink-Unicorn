package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "products")
public class Product extends ExtendedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private String name;

  private String origin;

  private BigDecimal purchasePricePer100g;

  private BigDecimal sellingPricePer100g;

  public UUID getId() {
    return id;
  }

  public ExtendedEntity setId(UUID id) {
    this.id = id;
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public BigDecimal getPurchasePricePer100g() {
    return purchasePricePer100g;
  }

  public void setPurchasePricePer100g(BigDecimal purchasePricePer100g) {
    this.purchasePricePer100g = purchasePricePer100g;
  }

  public BigDecimal getSellingPricePer100g() {
    return sellingPricePer100g;
  }

  public void setSellingPricePer100g(BigDecimal sellingPricePer100g) {
    this.sellingPricePer100g = sellingPricePer100g;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  private LocalDate harvestDate;
}