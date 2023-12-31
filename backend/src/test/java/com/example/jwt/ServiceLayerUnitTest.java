package com.example.jwt;
import com.example.jwt.domain.category.Category;
import com.example.jwt.domain.origin.Origin;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerUnitTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product dummyProduct;
    private List<Product> dummyProducts;

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
//UUID id, String variety, Category category, Origin origin, BigDecimal purchasePricePer100g, BigDecimal sellingPricePer100g, LocalDate harvestDate, int stock
        dummyProduct = new Product(productId1, variety1, category1, origin1, purchasePrice, sellingPrice, harvestDate, stock);
        dummyProducts = Stream.of(
                dummyProduct,
                new Product(productId2, variety2, category2, origin2, purchasePrice, sellingPrice, harvestDate, stock)
        ).collect(Collectors.toList());
    }

    @Test
    public void findById_requestProductById_expectProduct() throws Exception {
        given(productRepository.findById(any(UUID.class))).will(invocation -> {
            if ("non-existent".equals(invocation.getArgument(0)))
                throw new NoSuchElementException("No such product present");
            return Optional.of(dummyProduct);
        });

        assertThat(productService.findById(dummyProduct.getId())).usingRecursiveComparison().isEqualTo(dummyProduct);

        ArgumentCaptor<UUID> productArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(productRepository, times(1)).findById(productArgumentCaptor.capture());
        assertThat(productArgumentCaptor.getValue()).isEqualTo(dummyProduct.getId());
    }
}
