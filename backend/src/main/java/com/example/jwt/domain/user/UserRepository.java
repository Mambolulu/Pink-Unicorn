package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.example.jwt.domain.purchase.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendedRepository<User> {

  Optional<User> findByEmail(String email);

  @Query(value = "SELECT p.user, SUM(p.totalAmount) AS revenue FROM Purchase p " +
          "WHERE p.purchaseDate >= current_date - INTERVAL '30 days' AND p.purchaseDate <= current_date AND p.user.isActive = true " +
          "GROUP BY p.user ORDER BY revenue DESC LIMIT 1", nativeQuery = true)
  User findUserWithMostRevenueLastMonth();

  @Query(value = "SELECT p.origin, COUNT(p) FROM Products p WHERE p.orderDate >= CURRENT_DATE - :days GROUP BY p.origin ORDER BY COUNT(p) DESC", nativeQuery = true)
  Map<String, Integer> findTopCountriesByProductOrdersLastXDays(int days);

  @Query(value = "SELECT p.product, SUM(p.quantity) AS 'Anzahl Bestellter Produkte', SUM(p.collectedSeeds) AS 'Gesammelte Seeds' " +
          "FROM Purchase p " +
          "WHERE p.user.id = :userId " +
          "GROUP BY p.product", nativeQuery = true)
  List<Purchase> retrieveUsersPurchaseHistory(UUID userId);
}
