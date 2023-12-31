package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.origin.OriginCount;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  Optional<User> getAuthenticatedUser();

  boolean isUserAdmin();

  User getTopUsersByRevenueLastMonth();

  void updateRankBasedOnSeeds(User user);

  OriginCount findMostOrderedOrigin(LocalDate startDate);

  void updateRankBasedOnSeeds(User user);
}
