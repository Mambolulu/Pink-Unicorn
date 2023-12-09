package com.example.jwt.domain.user;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import com.example.jwt.domain.role.Role;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends ExtendedAuditEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "address")
  private String address;

  //TODO add zipCodeId and rankId
  private UUID zipCodeId;

  private UUID rankId;

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

  public User() {
  }

  public User(UUID id, String firstName, String lastName, LocalDate birthDate, String address, int plz, String email, String password, Set<Role> roles) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.address = address;
    this.email = email;
    this.password = password;
    this.roles = roles;
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

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public User setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public User setAddress(String address) {
    this.address = address;
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
}
