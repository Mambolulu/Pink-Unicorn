package com.example.jwt;
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
        String origin = "Test Origin";
        BigDecimal purchasePrice = new BigDecimal("10.00");
        BigDecimal sellingPrice = new BigDecimal("15.00");
        LocalDate harvestDate = LocalDate.now();
        int stock = 100;

        dummyProduct = new Product(productId1, "BBC", origin, purchasePrice, sellingPrice, harvestDate, stock);
        dummyProducts = Stream.of(
                dummyProduct,
                new Product(productId2, "Black", origin, purchasePrice, sellingPrice, harvestDate, stock)
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
