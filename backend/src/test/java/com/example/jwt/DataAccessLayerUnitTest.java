package com.example.jwt;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DataAccessLayerUnitTest {

    @Autowired
    ProductRepository productRepository;

    private List<Product> dummyProducts;

    @BeforeEach
    public void setUp() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        String origin = "Test Origin";
        BigDecimal purchasePrice = new BigDecimal("10.00");
        BigDecimal sellingPrice = new BigDecimal("15.00");
        LocalDate harvestDate = LocalDate.now();
        int stock = 100;

        dummyProducts = Stream.of(
                new Product(productId1, "BBC", origin, purchasePrice, sellingPrice, harvestDate, stock),
                new Product(productId2, "Black", origin, purchasePrice, sellingPrice, harvestDate, stock)
        ).collect(Collectors.toList());

        productRepository.saveAll(dummyProducts);
    }

    @Test
    public void findAll_requestAllProducts_expectAllProducts() {
        List<Product> retrievedProducts = productRepository.findAll();

        // Assert that the retrieved products list is not null and has the expected size
        assertThat(retrievedProducts).isNotNull();
        assertThat(retrievedProducts.size()).isEqualTo(dummyProducts.size());

        // Further assert that key fields match for each product
        for (int i = 0; i < retrievedProducts.size(); i++) {
            Product retrievedProduct = retrievedProducts.get(i);
            Product dummyProduct = dummyProducts.get(i);

            assertThat(retrievedProduct.getName()).isEqualTo(dummyProduct.getName());
            assertThat(retrievedProduct.getOrigin()).isEqualTo(dummyProduct.getOrigin());
            // Add more assertions for other important fields
        }
    }
}
