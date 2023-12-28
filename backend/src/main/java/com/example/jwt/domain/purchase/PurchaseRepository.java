package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends ExtendedRepository<Purchase> {
}
