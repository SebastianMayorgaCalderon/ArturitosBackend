package com.brainstation.arturitos.services;

import com.brainstation.arturitos.domain.Category;
import com.brainstation.arturitos.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public  CategoryService (CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll().stream().map(Category::new).collect(Collectors.toList());
    }

}
