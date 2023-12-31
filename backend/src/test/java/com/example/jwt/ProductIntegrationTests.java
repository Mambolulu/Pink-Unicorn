package com.example.jwt;

import com.example.jwt.core.security.helpers.AuthorizationSchemas;
import com.example.jwt.core.security.helpers.JwtProperties;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.AuthorityRepository;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
        category2.setName("Test Category 1");
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
    }

    @Test
    public void retrieveAll_requestAllProducts_expectAllProductsAsDTOS() throws Exception {
        Authority authority = authorityRepository.saveAndFlush(new Authority().setName("CAN_RETRIEVE_PRODUCTS"));
        Role role = roleRepository.saveAndFlush(new Role().setName("ROLE_TESTER").setAuthorities(Set.of(authority)));
        User user = userRepository.saveAndFlush(new User().setEmail("john@doe.com").setRoles(Set.of(role)));
        Category category1 = new Category(UUID.randomUUID(), "New Category 1");
        Category category2 = new Category(UUID.randomUUID(), "New Category 2");
        Origin origin1 = new Origin(UUID.randomUUID(), "New Origin 1");
        Origin origin2 = new Origin(UUID.randomUUID(), "New Origin 2");
        LocalDate date1 = LocalDate.of(2023, 12, 12);
        LocalDate date2 = LocalDate.of(2023, 12, 13);
        List<Product> dummyProducts = productRepository.saveAllAndFlush(Stream.of(new Product(UUID.randomUUID(), "New Variety 1", category1, origin1, BigDecimal.valueOf(10.00), BigDecimal.valueOf(12.00), date1,  49),
                new Product(UUID.randomUUID(), "New Variety 2", category2, origin2, BigDecimal.valueOf(11.00), BigDecimal.valueOf(13.00), date2,  8)).collect(Collectors.toList()));

        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .header(HttpHeaders.AUTHORIZATION, AuthorizationSchemas.BEARER + " " + generateToken(user.getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getId().toString(), dummyProducts.get(1).getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value(Matchers.containsInAnyOrder(dummyProducts.get(0).getCategory(), dummyProducts.get(1).getCategory())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price").doesNotExist());
    }
}
