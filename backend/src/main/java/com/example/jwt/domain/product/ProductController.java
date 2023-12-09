package com.example.jwt.domain.product;

import com.example.jwt.domain.product.dto.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

//    @PostMapping({"", "/"})
//    public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO productDTO) {
//        Product product = productMapper.fromProductDTO(productDTO);
//        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
//    }

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
        return new ResponseEntity<>(productService.findAll(PageRequest.of(page, pageSize, Sort.by("price").descending())), HttpStatus.OK);
    }

//    Not sure if still needed
//    @GetMapping({"/p={page}"})
//    public ResponseEntity<List<Product>> retrieveAllOnPage(@PathVariable int page) {
//        return new ResponseEntity<>(productService.findAll(PageRequest.of(page, 10, Sort.by("price").descending())), HttpStatus.OK);
//    }

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
