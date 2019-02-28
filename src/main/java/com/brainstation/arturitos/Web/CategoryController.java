package com.brainstation.arturitos.Web;

import com.brainstation.arturitos.domain.Category;
import com.brainstation.arturitos.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<MyResponce<List<Category>>> getAllCAtegories(){
        return new ResponseEntity<>(new MyResponce<>(this.categoryService.getAllCategories(),"ok"), HttpStatus.OK);
    }




}
