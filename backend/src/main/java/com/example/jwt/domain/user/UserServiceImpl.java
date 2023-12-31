package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.origin.OriginCount;
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
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;
import java.util.*;

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
    // Fetch the lowest rank
    Rank lowestRank = rankRepository.findAll().stream()
            .min(Comparator.comparing(Rank::getNeededSeeds))
            .orElseThrow(() -> new IllegalStateException("No ranks found"));

    // Set the lowest rank and encode the password
    user.setRank(lowestRank);
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    // Retrieve the 'Client' role from the database
    Role clientRole = ((UserRepository) repository).findRoleByName("CLIENT")
            .orElseThrow(() -> new IllegalStateException("Client role not found"));

    // Assign 'Client' role to the user
    user.getRoles().add(clientRole);
    user.setActive(true);

    // Save the user with the new role
    User savedUser = save(user);

    // Update rank based on seeds (if applicable)
    updateRankBasedOnSeeds(savedUser);

    return savedUser;
  }

  public Optional<User> getAuthenticatedUser() throws UsernameNotFoundException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      if (principal instanceof UserDetailsImpl) {
        return Optional.of(((UserDetailsImpl) principal).user());
      }
    }

    return Optional.empty();
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
  public OriginCount findMostOrderedOrigin(LocalDate startDate) {
    Object[] result = ((UserRepository) repository).findMostOrderedOrigin(startDate);

    OriginCount mostOrderedOrigin = new OriginCount();

    mostOrderedOrigin.setId(UUID.randomUUID());

    if (result != null && result.length == 2) {
      mostOrderedOrigin.setCountry((String) result[0]);
      mostOrderedOrigin.setOrderCount((int) result[1]);
    }

    return mostOrderedOrigin;
  }

  @Override
  public User getTopUsersByRevenueLastMonth() {
    LocalDate startDate = LocalDate.now().minusDays(30);
    LocalDate endDate = LocalDate.now();
    return ((UserRepository) repository).findUserWithMostRevenueLastMonth(startDate, endDate);
  }
}
