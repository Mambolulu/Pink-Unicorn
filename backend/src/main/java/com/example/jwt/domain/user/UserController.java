package com.example.jwt.domain.user;

import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.origin.dto.OriginDTO;
import com.example.jwt.domain.origin.dto.OriginMapper;
import com.example.jwt.domain.purchase.dto.PurchaseMapper;
import com.example.jwt.domain.user.dto.UserDTO;
import com.example.jwt.domain.user.dto.UserMapper;
import com.example.jwt.domain.user.dto.UserRegisterDTO;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;
  private final OriginMapper originMapper;
  private final PurchaseMapper purchaseMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper, OriginMapper originMapper, PurchaseMapper purchaseMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.originMapper = originMapper;
    this.purchaseMapper = purchaseMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> retrieveById(@PathVariable UUID id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }

  @GetMapping({"", "/"})
  public ResponseEntity<List<UserDTO>> retrieveAll() {
    List<User> users = userService.findAll();
    return new ResponseEntity<>(userMapper.toDTOs(users), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
    User user = userService.register(userMapper.fromUserRegisterDTO(userRegisterDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_MODIFY') && @userPermissionEvaluator.isUserAboveAge(authentication.principal.user,18)")
  public ResponseEntity<UserDTO> updateById(@PathVariable UUID id,
      @Valid @RequestBody UserDTO userDTO) {
    User user = userService.updateById(id, userMapper.fromDTO(userDTO));
    return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_DELETE')")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    userService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/statistics/highest/revenue")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<UserDTO> getTopCustomersByRevenueLastMonth() {
    User topUserByRevenue = userService.getTopUsersByRevenueLastMonth();
    return new ResponseEntity<>(userMapper.toDTO(topUserByRevenue), HttpStatus.OK);
  }

  @GetMapping("/statistics/products/highest/country")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<OriginDTO> getTopCountriesByProductOrders(@RequestParam(required = false, defaultValue = "30") int days) {
    Origin topCountry = userService.getTopCountriesByProductOrdersLastXDays(days);
    return new ResponseEntity<>(originMapper.toDTO(topCountry), HttpStatus.OK);
  }
}
