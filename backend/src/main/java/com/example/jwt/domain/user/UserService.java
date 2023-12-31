package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.origin.OriginCount;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.time.LocalDate;
import java.util.Optional;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  Optional<User> getAuthenticatedUser();

  User getTopUsersByRevenueLastMonth();

  OriginCount findMostOrderedOrigin(LocalDate startDate);

  void updateRankBasedOnSeeds(User user);
}
