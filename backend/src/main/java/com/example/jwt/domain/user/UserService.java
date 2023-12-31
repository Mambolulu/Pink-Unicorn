package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import com.example.jwt.domain.origin.OriginCount;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  User getTopUsersByRevenueLastMonth();

  void updateRankBasedOnSeeds(User user);

  OriginCount findMostOrderedOrigin(LocalDate startDate);
}
