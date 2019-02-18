package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository porductRepository;

    @Autowired
    public ProductService(ProductRepository porductRepository){
        this.porductRepository = porductRepository;
    }

    public Page<Product> findAll(Pageable pageable){
        return porductRepository.findAll(pageable).map(Product::new);
    }

    public Product findById(int id){

        return new Product(porductRepository.findById(id).orElse(null));
    }

}
