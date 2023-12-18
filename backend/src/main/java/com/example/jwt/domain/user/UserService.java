package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService, ExtendedService<User> {

  User register(User user);

  boolean isUserAdmin();

  public User getTopUsersByRevenueLastMonth();

  public Map<String, Integer> getTopCountriesByProductOrdersLastXDays(int days);
}
