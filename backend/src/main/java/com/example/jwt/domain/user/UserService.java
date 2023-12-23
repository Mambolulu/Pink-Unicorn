package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.purchase.Purchase;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  boolean isUserAdmin();

  User getTopUsersByRevenueLastMonth();

  Map<String, Integer> getTopCountriesByProductOrdersLastXDays(int days);

  List<Purchase> retrievePurchaseHistory();
}
