package com.example.jwt.domain.purchase.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.user.dto.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class PurchaseDTO extends ExtendedDTO {

    private UserDTO user;

    private Product product;

    private int quantity;

    private LocalDate purchaseDate;

    private BigDecimal totalAmount;

    private int collectedSeeds;

    public PurchaseDTO() { }

    public PurchaseDTO(UUID id, UserDTO user, Product product, int quantity, LocalDate purchaseDate, BigDecimal totalAmount, int collectedSeeds) {
        super(id);
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.collectedSeeds = collectedSeeds;
    }

    public UserDTO getUser() {
        return user;
    }

    public PurchaseDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public PurchaseDTO setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PurchaseDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public PurchaseDTO setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public PurchaseDTO setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public int getCollectedSeeds() {
        return collectedSeeds;
    }

    public PurchaseDTO setCollectedSeeds(int collectedSeeds) {
        this.collectedSeeds = collectedSeeds;
        return this;
    }
}
