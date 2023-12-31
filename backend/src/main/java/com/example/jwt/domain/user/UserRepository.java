package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedRepository;

import java.time.LocalDate;
import java.util.Optional;

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

  @Query("SELECT r FROM Role r WHERE r.name = :roleName")
  Optional<Role> findRoleByName(@Param("roleName") String roleName);

  @Query(value = "SELECT o.country AS country, COUNT(pu.id) AS orderCount " +
          "FROM origins o " +
          "INNER JOIN products p ON o.id = p.origin_id " +
          "INNER JOIN purchases pu ON p.id = pu.products " +
          "INNER JOIN users u ON pu.users = u.id " +
          "WHERE u.created_at >= :startDate " +
          "GROUP BY o.country " +
          "ORDER BY COUNT(pu.id) DESC " +
          "LIMIT 1", nativeQuery = true)
  Object[] findMostOrderedOrigin(@Param("startDate") LocalDate startDate);
}
