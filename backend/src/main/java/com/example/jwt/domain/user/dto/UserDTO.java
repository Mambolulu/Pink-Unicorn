package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.location.zipcode.dto.ZipCodeDTO;
import com.example.jwt.domain.rank.dto.RankDTO;
import com.example.jwt.domain.role.dto.RoleDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class UserDTO extends ExtendedDTO {

  private String firstName;

  private String lastName;

  private LocalDate birthDate;

  private String address;

  @Valid
  private ZipCodeDTO zipCode;

  @Valid
  private RankDTO rank;

  @Email
  private String email;

  @Valid
  private Set<RoleDTO> roles;

  public UserDTO() {
  }

  public UserDTO(UUID id, String firstName, String lastName, LocalDate birthDate, String address, ZipCodeDTO zipCode, RankDTO rank, String email, Set<RoleDTO> roles) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.address = address;
    this.zipCode = zipCode;
    this.rank = rank;
    this.email = email;
    this.roles = roles;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public UserDTO setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public ZipCodeDTO getZipCode() {
    return zipCode;
  }

  public UserDTO setZipCode(ZipCodeDTO zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public RankDTO getRank() {
    return rank;
  }

  public UserDTO setRank(RankDTO rank) {
    this.rank = rank;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public Set<RoleDTO> getRoles() {
    return roles;
  }

  public UserDTO setRoles(Set<RoleDTO> roles) {
    this.roles = roles;
    return this;
  }
}
