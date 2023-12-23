package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.purchase.Purchase;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.rank.Rank;
import com.example.jwt.domain.rank.RankRepository;
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

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;
import java.util.Comparator;

@Service
public class UserServiceImpl extends ExtendedServiceImpl<User> implements UserService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RankRepository rankRepository;

  @Autowired
  public UserServiceImpl(UserRepository repository, Logger logger,
      BCryptPasswordEncoder bCryptPasswordEncoder, RankRepository rankRepository) {
    super(repository, logger);
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.rankRepository = rankRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return ((UserRepository) repository).findByEmail(email).map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(email));
  }

  @Override
  public User register(User user) {
    Rank lowestRank = rankRepository.findAll().stream()
            .min(Comparator.comparing(Rank::getNeededSeeds))
            .orElseThrow(() -> new IllegalStateException("No ranks found"));

    user.setRank(lowestRank); // Set the lowest rank
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    User savedUser = save(user);

    updateRankBasedOnSeeds(savedUser);

    return savedUser;
  }

  public void updateRankBasedOnSeeds(User user) {
    // Logic to update the rank based on the seeds
    int userSeeds = user.getSeeds();
    Rank newRank = rankRepository.findAll().stream()
            .filter(rank -> userSeeds >= rank.getNeededSeeds())
            .max(Comparator.comparing(Rank::getNeededSeeds))
            .orElse(user.getRank());

    user.setRank(newRank);
    save(user);
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
