package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.location.zipcode.dto.ZipCodeDTO;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRegisterDTO extends ExtendedDTO {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private LocalDate birthDate;

  @NotNull
  private String address;

  @Valid
  private ZipCodeDTO zipCode;
  private int plz;

  @Email
  private String email;

  @NotNull
  private String password;


  public UserRegisterDTO() {
  }

  public UserRegisterDTO(UUID id, String firstName, String lastName, LocalDate birthDate, String address, ZipCodeDTO zipCode, String email, String password) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.address = address;
    this.zipCode = zipCode;
    this.email = email;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserRegisterDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegisterDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public UserRegisterDTO setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserRegisterDTO setAddress(String address) {
    this.address = address;
    return this;
  }

  public ZipCodeDTO getZipCode() {
    return zipCode;
  }

  public UserRegisterDTO setZipCode(ZipCodeDTO zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegisterDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegisterDTO setPassword(String password) {
    this.password = password;
    return this;
  }
}
