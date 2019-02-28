package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.ProductImagesDTO;

public class ProductImage {
    private String imageUrl;
    private String imageDescription;

    public ProductImage() {
    }

    public ProductImage(ProductImagesDTO productImagesDTO){
        this.imageDescription = productImagesDTO.getDescription();
        this.imageUrl = productImagesDTO.getImageURl();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}
