package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.rank.Rank;
import com.example.jwt.domain.rank.RankRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
