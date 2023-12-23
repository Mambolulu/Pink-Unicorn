package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "purchases")
public class Purchase extends ExtendedEntity {

    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "products", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "colected_Seeds")
    private int collectedSeeds;

    public Purchase() { }

    public Purchase(UUID id, User user, Product product, int quantity, LocalDate purchaseDate, BigDecimal totalAmount, int collectedSeeds) {
        super(id);
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.collectedSeeds = collectedSeeds;
    }

    public User getUser() {
        return user;
    }

    public Purchase setUser(User user) {
        this.user = user;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Purchase setProduct(Product product) {
        this.product = product;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Purchase setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Purchase setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Purchase setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public int getCollectedSeeds() {
        return collectedSeeds;
    }

    public Purchase setCollectedSeeds(int colectedSeeds) {
        this.collectedSeeds = colectedSeeds;
        return this;
    }
}

