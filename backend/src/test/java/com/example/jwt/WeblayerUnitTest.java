package com.example.jwt;

import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import static org.mockito.Mockito.when;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductService;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    dummyProduct = new Product(UUID.randomUUID() ,"BBC");
    dummyProducts = Stream.of(new Product(UUID.randomUUID(), "BBC"), new Product(UUID.randomUUID(), "Black")).collect(Collectors.toList());
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
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getName(), dummyProducts.get(1).getName())))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").doesNotExist());

    verify(productService, times(1)).findAll(pageRequest);
  }
}
