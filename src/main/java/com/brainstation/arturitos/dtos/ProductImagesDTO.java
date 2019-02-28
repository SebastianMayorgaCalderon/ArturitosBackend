package com.brainstation.arturitos.dtos;

import javax.persistence.*;

@Entity(name = "ProductImages")
@Table(name = "product_images")
public class ProductImagesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private int id;

    @Column(name = "image_url")
    private String imageURl;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private ProductDTO productDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }


}
