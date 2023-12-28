package com.example.jwt.domain.purchase;

import com.example.jwt.core.exception.InsufficientStockException;
import com.example.jwt.core.exception.ProductNotFoundException;
import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.product.ProductService;
import com.example.jwt.domain.product.PurchaseResult;
import com.example.jwt.domain.purchase.dto.PurchaseDTO;
import com.example.jwt.domain.rank.Rank;
import com.example.jwt.domain.rank.RankRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserDetailsImpl;
import com.example.jwt.domain.user.UserRepository;
import com.example.jwt.domain.user.UserService;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

@Service
public class PurchaseServiceImpl extends ExtendedServiceImpl<Purchase> implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final RankRepository rankRepository;
    private final ProductService productService;
    private final UserService userService;

    protected PurchaseServiceImpl(ExtendedRepository<Purchase> repository, Logger logger, PurchaseRepository purchaseRepository, UserRepository userRepository, ProductRepository productRepository, RankRepository rankRepository, ProductService productService, UserService userService) {
        super(repository, logger);
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.rankRepository = rankRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public Purchase placeOrder(UUID productId, double quantity) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = ((User) authentication.getPrincipal()).getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.user();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));;

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock available");
        }

        BigDecimal sellingPrice = product.getSellingPricePer100g();
        double discount = user.getRank().getDiscount();
        double totalPrice = quantity * sellingPrice.doubleValue() * (1 - discount);

        product.setStock((int)(product.getStock() - quantity));
        productRepository.save(product);

        int seedsInThisPurchase = user.calculateAndAddSeeds(totalPrice);
        userRepository.save(user);

        userService.updateRankBasedOnSeeds(user);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setQuantity((int) quantity);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setTotalAmount(BigDecimal.valueOf(totalPrice));
        purchase.setCollectedSeeds(seedsInThisPurchase);

        purchaseRepository.save(purchase);

        purchase = purchaseRepository.findById(purchase.getId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch saved purchase"));

        return purchase;
    }
}
