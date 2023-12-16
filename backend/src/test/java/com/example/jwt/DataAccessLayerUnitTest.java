package com.example.jwt;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

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
        dummyProducts = Stream.of(new Product(UUID.randomUUID(), "BBC"), new Product(UUID.randomUUID(), "Black")).collect(Collectors.toList());
    }
}
