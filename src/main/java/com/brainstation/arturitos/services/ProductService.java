package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Product;
import com.brainstation.arturitos.dtos.CategoryDTO;
import com.brainstation.arturitos.dtos.ProductDTO;
import com.brainstation.arturitos.repositories.CategoryRepository;
import com.brainstation.arturitos.repositories.ProductRepository;
import com.brainstation.arturitos.utils.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository porductRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository porductRepository, CategoryRepository categoryRepository){
        this.porductRepository = porductRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> findAll(Pageable pageable){
            return porductRepository.findAll(pageable).map(Product::new);
    }

    public Product findById(int id) throws MyExeption {
        Optional<ProductDTO> productDTO = porductRepository.findById(id);
        if(productDTO.isPresent()){
            return new Product(productDTO.get());
        }else{
            throw new MyExeption("Not found");
        }

    }

    public Page<Product> findByCategroy(String category, Pageable pageable) throws MyExeption{
        Optional<CategoryDTO> categoryDTO = categoryRepository.findByName(category);
        if(categoryDTO.isPresent()){
            return porductRepository.findAllByCategoryDTOSContains(pageable, categoryDTO.get()).map(Product::new);
        }else{
            throw new MyExeption("Category does not exist");
        }
    }

}
