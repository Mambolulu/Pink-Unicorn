package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.purchase.Purchase;
import com.example.jwt.domain.role.Role;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email).map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return save(user);
  }

  @Override
  public boolean isUserAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()){
      Object principal = authentication.getPrincipal();

      if (principal instanceof UserDetails) {   // Überprüfen, ob das Principal-Objekt UserDetails implementiert
        User user = (User) principal;    // Hier gehen wir davon aus, dass Ihre User-Objekte eine getRoles()-Methode haben
        Set<Role> roles = user.getRoles();

        if (roles.contains("ADMIN")) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public User getTopUsersByRevenueLastMonth() {
    return ((UserRepository) repository).findUserWithMostRevenueLastMonth();
  }

  @Override
  public Map<String, Integer> getTopCountriesByProductOrdersLastXDays(int days) {
    return ((UserRepository) repository).findTopCountriesByProductOrdersLastXDays(days);
  }

  @Override
  public List<Purchase> retrievePurchaseHistory(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UUID userId = ((User) authentication.getPrincipal()).getId();

    return ((UserRepository) repository).retrieveUsersPurchaseHistory(userId);
  }
}
