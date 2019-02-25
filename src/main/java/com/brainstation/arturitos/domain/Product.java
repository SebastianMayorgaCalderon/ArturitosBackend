package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.ProductDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
    private int id;
    private String productName;
    private String description;
    private String seller;
    private String price;
    private List<ProductImage> productImages;
    private List<Category> categories;

    public Product(){}

    public Product(ProductDTO productDTO){
        this.price = productDTO.getPrice().toString();
        this.categories = productDTO.getCategoryDTOS().stream().map(Category::new).collect(Collectors.toList());
        this.productImages = productDTO.getImagesDTOS().stream().map(ProductImage::new).collect(Collectors.toList());
        this.productName = productDTO.getName();
        this.description = productDTO.getDesription();
        this.seller = productDTO.getSeller();
        this.id= productDTO.getId();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
