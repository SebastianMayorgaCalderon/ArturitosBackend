package com.brainstation.arturitos.services;

import com.brainstation.arturitos.repositories.ProductImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    private ProductImageRepository productImageRepository;
    public ProductImageService(ProductImageRepository productImageRepository){
        this.productImageRepository = productImageRepository;
    }
}
