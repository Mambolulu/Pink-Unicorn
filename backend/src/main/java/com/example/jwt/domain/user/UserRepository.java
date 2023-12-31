package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.purchase.Purchase;
import com.example.jwt.domain.role.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendedRepository<User> {

  Optional<User> findByEmail(String email);

  @Query(value ="SELECT u.* FROM users u, purchases p " +
          "WHERE u.id = p.users AND u.is_active = 'true' AND p.purchase_date >= :startDate AND p.purchase_date <= :endDate  " +
          "GROUP BY u.id " +
          "ORDER BY SUM(p.total_amount) " +
          "DESC LIMIT 1", nativeQuery = true)
  User findUserWithMostRevenueLastMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  /*@Query(value = "SELECT p.origin, COUNT(p) FROM Products p WHERE p.orderDate >= CURRENT_DATE - :days GROUP BY p.origin ORDER BY COUNT(p) DESC", nativeQuery = true)*/
  @Query(value = "SELECT o.* FROM purchases p, products, categories, origins o " +
          "WHERE p.purchase_date >= CURRENT_DATE - :days " +
          "AND p.products = products.id " +
          "AND products.category_id = categories.id " +
          "AND o.id = products.origin_id " +
          "GROUP BY o.id " +
          "ORDER BY COUNT(p.quantity) " +
          "DESC LIMIT 1",nativeQuery = true)
  Origin findTopCountriesByProductOrdersLastXDays(int days);

  @Query(value = "SELECT p.product, SUM(p.quantity) AS 'Anzahl Bestellter Produkte', SUM(p.collectedSeeds) AS 'Gesammelte Seeds' " +
          "FROM Purchase p " +
          "WHERE p.user.id = :userId " +
          "GROUP BY p.product", nativeQuery = true)
  List<Purchase> retrieveUsersPurchaseHistory(UUID userId);

  @Query("SELECT r FROM Role r WHERE r.name = :roleName")
  Optional<Role> findRoleByName(@Param("roleName") String roleName);
}
