package com.example.jwt.domain.product;

import com.example.jwt.core.generic.ExtendedRepository;
import com.example.jwt.core.generic.ExtendedServiceImpl;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ExtendedServiceImpl<Product> implements ProductService {

  protected ProductServiceImpl(ExtendedRepository<Product> repository, Logger logger) {
    super(repository, logger);
  }
}
