package com.example.jwt.domain.purchase;

import com.example.jwt.core.exception.InsufficientStockException;
import com.example.jwt.core.exception.ProductNotFoundException;
import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import com.example.jwt.domain.product.Product;
import com.example.jwt.domain.product.ProductRepository;
import com.example.jwt.domain.user.User;
import com.example.jwt.domain.user.UserDetailsImpl;
import com.example.jwt.domain.user.UserRepository;
import com.example.jwt.domain.user.UserService;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseServiceImpl extends ExtendedServiceImpl<Purchase> implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    protected PurchaseServiceImpl(ExtendedRepository<Purchase> repository, Logger logger, PurchaseRepository purchaseRepository, UserRepository userRepository, ProductRepository productRepository, UserService userService) {
        super(repository, logger);
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public Purchase placeOrder(UUID productId, double quantityInGram) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.user();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));;

        if (product.getStockInGram() < quantityInGram) {
            throw new InsufficientStockException("Not enough stock available");
        }

        double sellingPricePer100g = product.getSellingPricePer100g().doubleValue();
        double discount = 1 - (user.getRank().getDiscount() / 100); // for example discount is 11, needs to be 0.89
        double totalPrice = sellingPricePer100g / 100 * quantityInGram * discount;
        double roundedTotalPrice = Math.round(totalPrice * 20) / 20.0;

        product.setStockInGram((int)(product.getStockInGram() - quantityInGram));
        productRepository.save(product);

        int seedsInThisPurchase = user.calculateAndAddSeeds(roundedTotalPrice);
        userRepository.save(user);

        userService.updateRankBasedOnSeeds(user);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setQuantity((int) quantityInGram);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setTotalAmount(BigDecimal.valueOf(roundedTotalPrice));
        purchase.setCollectedSeeds(seedsInThisPurchase);

        purchaseRepository.save(purchase);

        purchase = purchaseRepository.findById(purchase.getId())
                .orElseThrow(() -> new RuntimeException("Failed to fetch saved purchase"));

        return purchase;
    }

    public List<PurchaseSummary> retrievePurchaseHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.user();
        List<Object[]> purchaseSummaries = purchaseRepository.findPurchaseSummaryByUser(user.getId());

        List<PurchaseSummary> purchaseHistory = new ArrayList<>();

        for (Object[] row : purchaseSummaries) {
            PurchaseSummary summary = new PurchaseSummary();
            summary.setId(UUID.randomUUID());
            summary.setTeaVariety((String) row[0]);
            summary.setTotalOrderedQuantity(((BigInteger) row[1]).intValue());
            summary.setTotalCollectedSeeds(((BigInteger) row[2]).intValue());
            purchaseHistory.add(summary);
        }

        return purchaseHistory;
    }
}
