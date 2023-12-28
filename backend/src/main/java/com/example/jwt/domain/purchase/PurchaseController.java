package com.example.jwt.domain.purchase;

import com.example.jwt.domain.purchase.dto.PurchaseDTO;
import com.example.jwt.domain.purchase.dto.PurchaseMapper;
import com.example.jwt.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final UserService userService;
    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseController(UserService userService, PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
    }

    @PostMapping("/{productId}/{quantityInGram}")
    @PreAuthorize("hasAuthority('CAN_PLACE_ORDER')")
    public ResponseEntity<PurchaseDTO> placeOrder(
            @PathVariable UUID productId,
            @PathVariable double quantityInGram)
    {
        Purchase purchase = purchaseService.placeOrder(productId, quantityInGram);
        return new ResponseEntity<>(purchaseMapper.toDTO(purchase), HttpStatus.OK);
    }

    @GetMapping("/retrieve/purchase/history")
    @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
    public ResponseEntity<List<PurchaseDTO>> retrievePurchaseHistory() {
        List<Purchase> purchases = userService.retrievePurchaseHistory();
        return new ResponseEntity<>(purchaseMapper.toDTOs(purchases), HttpStatus.NO_CONTENT);
    }
}
