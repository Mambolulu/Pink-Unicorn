package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.role.Role;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends ExtendedAuditEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_role",
      joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles = new HashSet<>();

  @Column(name = "customer_rank")
  private CustomerRank customerRank;

  @Column(name = "seeds")
  private int seeds;

  public User() {
  }

  public User(UUID id, String firstName, String lastName, String email, String password,
      Set<Role> roles) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.customerRank;
    this.seeds;
  }

  public String getFirstName() {
    return firstName;
  }

  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public User setRoles(Set<Role> roles) {
    this.roles = roles;
    return this;
  }

  public CustomerRank getCustomerRank() {
    return customerRank;
  }

  public void setCustomerRank(CustomerRank customerRank) {
    this.customerRank = customerRank;
  }

  public int getSeeds() {
    return seeds;
  }

  public void setSeeds(int seeds) {
    this.seeds = seeds;
  }

  // Methode zur Berechnung der Seeds basierend auf dem Kaufpreis
  public void calculateAndAddSeeds(double purchasePrice) {
    int earnedSeeds = (int) (purchasePrice / 2);
    this.seeds += earnedSeeds;
    updateCustomerRank();
  }

  // Methode zur Aktualisierung des Kundenranges
  private void updateCustomerRank() {
    if (this.seeds >= 300) {
      this.customerRank = CustomerRank.DIAMOND;
    } else if (this.seeds >= 140) {
      this.customerRank = CustomerRank.PLATINUM;
    } else if (this.seeds >= 60) {
      this.customerRank = CustomerRank.GOLD;
    } else if (this.seeds >= 20) {
      this.customerRank = CustomerRank.SILVER;
    } else if (this.seeds >= 0) {
      this.customerRank = CustomerRank.BRONZE;
    }
  }

  public boolean canPlaceOrder() {
    return this.roles.stream()
            .anyMatch(role -> role.getAuthorities().contains(Authority.CAN_PLACE_ORDER));
  }
}
