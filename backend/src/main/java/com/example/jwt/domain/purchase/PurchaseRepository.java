package com.example.jwt.domain.purchase;

import com.example.jwt.core.generic.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends ExtendedRepository<Purchase> {

    @Query(value = "SELECT pr.variety AS teaVariety, " +
            "SUM(p.quantity) AS totalOrderedQuantity, " +
            "SUM(p.collected_seeds) AS totalCollectedSeeds " +
            "FROM purchases p " +
            "JOIN products pr ON p.products = pr.id " +
            "WHERE p.users = :userId " +
            "GROUP BY p.products, pr.variety", nativeQuery = true)
    List<Object[]> findPurchaseSummaryByUser(@Param("userId") UUID userId);

}
