package com.example.jwt;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
@AutoConfigureTestDatabase
public class DataAccessLayerUnitTest {

    @Autowired
    ProductRepository productRepository;

    private List<Product> dummyProducts;

    @BeforeEach
    public void setUp() {
        /*UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID categoryId1 = UUID.randomUUID();
        UUID categoryId2 = UUID.randomUUID();
        UUID originId1 = UUID.randomUUID();
        UUID originId2 = UUID.randomUUID();*/
        String variety1 = "Test Variety 1";
        String variety2 = "Test Variety 2";
        Category category1 = new Category();

        category1.setName("Test Category 1");
        Category category2 = new Category();

        category2.setName("Test Category 2");
        Origin origin1 = new Origin();

        origin1.setCountry("Test Country 1");
        Origin origin2 = new Origin();

        origin2.setCountry("Test Country 2");
        BigDecimal purchasePrice = new BigDecimal("10.00");
        BigDecimal sellingPrice = new BigDecimal("15.00");
        LocalDate harvestDate = LocalDate.now();
        int stock = 100;
//UUID id, String variety, Category category, Origin origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stock
        dummyProducts = Stream.of(
                new Product(variety1, category1, origin1, purchasePrice, sellingPrice, harvestDate, stock),
                new Product(variety2, category2, origin2, purchasePrice, sellingPrice, harvestDate, stock)
        ).collect(Collectors.toList());

        List<Product> blabliblu = productRepository.saveAll(dummyProducts);
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


            assertThat(retrievedProduct.getOrigin()).isEqualTo(dummyProduct.getOrigin());
            assertThat(retrievedProduct.getCategory()).isEqualTo(dummyProduct.getCategory());

            // Add more assertions for other important fields
        }
    }
}
