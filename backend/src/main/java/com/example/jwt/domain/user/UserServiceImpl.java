package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.origin.Origin;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
  public User getTopUsersByRevenueLastMonth() {
    LocalDate startDate = LocalDate.now().minusDays(30);
    LocalDate endDate = LocalDate.now();
    return ((UserRepository) repository).findUserWithMostRevenueLastMonth(startDate, endDate);
  }

  @Override
  public Origin getTopCountriesByProductOrdersLastXDays(int days) {
    return ((UserRepository) repository).findTopCountriesByProductOrdersLastXDays(days);
  }

  @Override
  public List<Purchase> retrievePurchaseHistory(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UUID userId = ((User) authentication.getPrincipal()).getId();

    return ((UserRepository) repository).retrieveUsersPurchaseHistory(userId);
  }
}
