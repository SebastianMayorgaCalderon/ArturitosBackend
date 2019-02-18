package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Product {
    private String productName;
    private String description;
    private String seller;
    private double price;
    private int likes, dislikes;
    private List<ProductImage> productImages;
    private List<Category> categories;

    public Product(){}

    public Product(ProductDTO productDTO){
        this.price = productDTO.getPrice();
        this.categories = productDTO.getCategoryDTOS().stream().map(Category::new).collect(Collectors.toList());
        this.productImages = productDTO.getImagesDTOS().stream().map(ProductImage::new).collect(Collectors.toList());
        this.productName = productDTO.getName();
        this.description = productDTO.getDesription();
        this.seller = productDTO.getSeller();
        this.likes = productDTO.getLikes();
        this.dislikes= productDTO.getDislikes();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
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
}
