package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.purchase.Purchase;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  User getTopUsersByRevenueLastMonth();

  Origin getTopCountriesByProductOrdersLastXDays(int days);

  List<Purchase> retrievePurchaseHistory();

  public void updateRankBasedOnSeeds(User user);
}
