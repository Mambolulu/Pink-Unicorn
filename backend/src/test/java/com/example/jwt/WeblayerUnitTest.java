package com.example.jwt;

import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductService;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc()
class WeblayerUnitTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JwtProperties jwtProperties;

  @MockBean
  private UserService userService;

  @MockBean
  private ProductService productService;

  private String dummyToken;
  private Product dummyProduct;
  private List<Product> dummyProducts;

  private String generateToken() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

    return Jwts.builder()
            .setClaims(Map.of("sub", UUID.randomUUID().toString()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
            .setIssuer(jwtProperties.getIssuer())
            .signWith(Keys.hmacShaKeyFor(keyBytes))
            .compact();
  }

  @BeforeEach
  public void setUp() {
    dummyToken = generateToken();

    // Example values for initializing Product objects
    UUID productId1 = UUID.randomUUID();
    UUID productId2 = UUID.randomUUID();
    UUID categoryId1 = UUID.randomUUID();
    UUID categoryId2 = UUID.randomUUID();
    UUID originId1 = UUID.randomUUID();
    UUID originId2 = UUID.randomUUID();
    String variety1 = "Test Variety 1";
    String variety2 = "Test Variety 2";
    Category category1 = new Category();
    category1.setId(categoryId1);
    category1.setName("Test Category 1");
    Category category2 = new Category();
    category2.setId(categoryId2);
    category2.setName("Test Category 2");
    Origin origin1 = new Origin();
    origin1.setId(originId1);
    origin1.setCountry("Test Country 1");
    Origin origin2 = new Origin();
    origin2.setId(originId2);
    origin2.setCountry("Test Country 2");
    BigDecimal purchasePrice = new BigDecimal("10.00");
    BigDecimal sellingPrice = new BigDecimal("15.00");
    LocalDate harvestDate = LocalDate.now();
    int stock = 100;

    // Creating Product objects with the full constructor
    dummyProducts = Stream.of(
            new Product(productId1, variety1, category1, origin1, purchasePrice, sellingPrice, harvestDate, stock),
            new Product(productId2, variety2, category2, origin2, purchasePrice, sellingPrice, harvestDate, stock)
    ).collect(Collectors.toList());
  }


  @Test
    /*
        @WithUserDetails(setupBefore = TestExecutionEvent.TEST_EXECUTION, userDetailsServiceBeanName = "userServiceImpl", value = "robert@gmail.com")
        Above annotation invokes the [userDetailsServiceBeanName].loadUserByUsername(String email) method with the parameter [value]. Hence, the method
        loadUserByUsername(String email) needs to be mocked accordingly. Even though the returned user is saved as principal in the security context, the
        result of the test method stays "FALSE: Status Expected:<200> but was:<403>". This is due to the fact that WebSecurityConfig.filterChain()
        gets invoked by mvc.perform() AFTER the annotation @WithUserDetails got triggered. This leads to a SecurityContextHolder.clearContext() by
        CustomAuthorizationFilter as no valid JWT was passed to doFilterInternal(). Following approach solves the given issue:
        -   Pass a dummy JWT to the requests triggered by mvc.perform()
        -   Mock the method UserService.findById and return the desired users with the requested roles and authorities
    */
  public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
    PageRequest pageRequest = PageRequest.of(0, 10);
    given(userService.findById(any(UUID.class))).willReturn(
            new User().setRoles(Set.of(new Role().setAuthorities(Set.of(new Authority().setName("CAN_RETRIEVE_PRODUCTS"))))));
    when(productService.findAll(any(Pageable.class))).thenReturn(dummyProducts);

    mvc.perform(MockMvcRequestBuilders
                    .get("/products")
                    .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + dummyToken)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())));

    verify(productService, times(1)).findAll(pageRequest);
  }
}
