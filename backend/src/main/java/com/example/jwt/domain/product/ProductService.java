package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.user.User;

import java.util.UUID;

public interface ProductService extends ExtendedService<Product> {
    PurchaseResult purchaseProduct(User buyer, UUID productId, double quantity);
}
