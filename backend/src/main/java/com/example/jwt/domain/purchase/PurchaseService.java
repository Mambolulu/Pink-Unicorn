package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedService;

import java.util.UUID;

public interface PurchaseService extends ExtendedService<Purchase> {

    Purchase placeOrder(UUID productId, double quantity);

}
