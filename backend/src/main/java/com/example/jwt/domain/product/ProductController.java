package com.example.jwt.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_SEE_PRODUCTS') && @productPermissionEvaluator.doProductsIncludeId(authentication.principal.user,#id)")
    public ResponseEntity<Product> retrieveById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping({"", ""})
    @PreAuthorize("hasAuthority('CAN_RETRIEVE_PRODUCTS')")
    public ResponseEntity<List<Product>> retrieveAll(
            @RequestParam(required=false, defaultValue = "0") int page,
            @RequestParam(required=false, defaultValue = "10") int pageSize
    ) {
        return new ResponseEntity<>(productService.findAll(PageRequest.of(page, pageSize)), HttpStatus.OK);
    }

    @PutMapping({"", "/"})
    public ResponseEntity<Product> updateById(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateById(@PathVariable UUID id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateById(product.getId(), product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.NO_CONTENT);
    }
}
