package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedService;

import java.util.List;
import java.util.UUID;

public interface PurchaseService extends ExtendedService<Purchase> {

    List<PurchaseSummary> retrievePurchaseHistory();

    Purchase placeOrder(UUID productId, double quantity);

}
