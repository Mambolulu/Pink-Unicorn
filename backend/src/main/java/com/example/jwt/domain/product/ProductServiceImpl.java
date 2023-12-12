package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ExtendedServiceImpl<Product> implements ProductService {
  @Autowired
  public ProductServiceImpl(ProductRepository repository, Logger logger) {
    super(repository, logger);
  }
}
