package com.example.jwt.domain.product.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDTO extends ExtendedDTO {

    private UUID id;
    private String name;
    private String origin;
    private BigDecimal purchasePricePer100g;
    private BigDecimal sellingPricePer100g;
    private LocalDate harvestDate;

  public ProductDTO(UUID id, String name, String origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate) {
    super(id);
    this.name = name;
    this.origin = origin;
    this.purchasePricePer100g = purchasePricePer100g;
    this.sellingPricePer100g = sellingPricePer100g;
    this.harvestDate = harvestDate;
  }

  @Override
  public UUID getId() {
    return id;
  }

  public ExtendedDTO setId(UUID id) {
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
}