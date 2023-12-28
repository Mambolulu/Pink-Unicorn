package com.example.jwt.domain.user.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.location.zipcode.dto.ZipCodeDTO;

import java.time.LocalDate;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class UserRegisterDTO extends ExtendedDTO {

  @NotBlank(message = "{validation.notblank.firstname}")
  private String firstName;

  @NotBlank(message = "{validation.notblank.lastname}")
  private String lastName;

  @NotNull(message = "{validation.notnull.birthdate}")
  @Past(message = "{validation.past.birthdate}")
  private LocalDate birthDate;

  @NotBlank(message = "{validation.notblank.address}")
  private String address;

  @Valid
  private ZipCodeDTO zipCode;

  @NotBlank(message = "{validation.notblank.email}")
  @Email(message = "{validation.email.email}")
  private String email;

  @NotBlank(message = "{validation.notblank.password}")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&:;,.?/~_+-=|\\\\]).{8,32}$",
          message = "{validation.pattern.password}")
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
