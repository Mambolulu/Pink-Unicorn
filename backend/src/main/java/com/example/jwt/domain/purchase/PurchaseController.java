package com.example.jwt.domain.purchase;

import com.example.jwt.domain.purchase.dto.PurchaseDTO;
import com.example.jwt.domain.purchase.dto.PurchaseMapper;
import com.example.jwt.domain.purchase.dto.PurchaseSummaryDTO;
import com.example.jwt.domain.purchase.dto.PurchaseSummaryMapper;
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

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseSummaryMapper purchaseSummaryMapper;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper, PurchaseSummaryMapper purchaseSummaryMapper) {
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
        this.purchaseSummaryMapper = purchaseSummaryMapper;
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

    @GetMapping("/retrieve/history")
    @PreAuthorize("hasAuthority('CAN_RETRIEVE_PURCHASE_HISTORY')")
    public ResponseEntity<List<PurchaseSummaryDTO>> retrievePurchaseHistory() {
        List<PurchaseSummary> purchaseSummaries = purchaseService.retrievePurchaseHistory();
        return new ResponseEntity<>(purchaseSummaryMapper.toDTOs(purchaseSummaries), HttpStatus.OK);
    }
}
