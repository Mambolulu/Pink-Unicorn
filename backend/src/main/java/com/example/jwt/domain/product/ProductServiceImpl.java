package com.example.jwt.domain.product;

import com.example.jwt.core.exception.InsufficientStockException;
import com.example.jwt.core.exception.ProductNotFoundException;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl extends ExtendedServiceImpl<Product> implements ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository; // Angenommen, Sie haben eine UserRepository zum Aktualisieren der Benutzerdaten

  @Autowired
  public ProductServiceImpl(ProductRepository repository, Logger logger, UserRepository userRepository) {
    super(repository, logger);
    this.productRepository = repository;
    this.userRepository = userRepository;
  }

  @Override
  public PurchaseResult purchaseProduct(User buyer, UUID productId, double quantity) {
    Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    if (product.getStock() < quantity) {
      throw new InsufficientStockException("Not enough stock available");
    }

    if (!buyer.canPlaceOrder()) {
      throw new RuntimeException("User is not authorized to place order"); // Passende Ausnahme verwenden
    }

    double discountRate = buyer.getCustomerRank().getDiscount();
    double totalPrice = quantity * product.getSellingPricePer100g().doubleValue() * (1 - discountRate);

    product.setStock((int)(product.getStock() - quantity)); // Konvertierung zu int
    productRepository.save(product);

    buyer.calculateAndAddSeeds(totalPrice);
    userRepository.save(buyer);

    return new PurchaseResult(totalPrice, buyer.getSeeds());
  }
}
