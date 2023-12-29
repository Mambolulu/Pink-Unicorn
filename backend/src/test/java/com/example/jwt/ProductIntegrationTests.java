package com.example.jwt;

import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.AuthorityRepository;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.RoleRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private String generateToken(UUID subject) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());

        return Jwts.builder()
                .setClaims(Map.of("sub", subject))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
                .setIssuer(jwtProperties.getIssuer())
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
        Authority authority = authorityRepository.saveAndFlush(new Authority().setName("USER_READ"));
        Role role = roleRepository.saveAndFlush(new Role().setName("ROLE_TESTER").setAuthorities(Set.of(authority)));
        User user = userRepository.saveAndFlush(new User().setEmail("john@doe.com").setRoles(Set.of(role)));
        List<Product> dummyProducts = productRepository.saveAllAndFlush(Stream.of(new Product(UUID.randomUUID(), "shirt", 49), new Product(UUID.randomUUID(), "sandwich", 8)).collect(Collectors.toList()));

        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + generateToken(user.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getName(), dummyProducts.get(1).getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").doesNotExist());
    }
}
