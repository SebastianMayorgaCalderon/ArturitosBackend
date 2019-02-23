package com.brainstation.arturitos.dtos;

import com.brainstation.arturitos.domain.Category;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Product")
@Table(name = "product")
public class ProductDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="seller")
    private String seller;

    @Column(name="price")
    private double price;

    @Column(name = "description")
    private String desription;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<ProductImagesDTO> imagesDTOS;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<ProductOrderDTO> productOrderDTOS;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<CommentDTO> comments;

    @ManyToMany(mappedBy = "products")
    private List<CategoryDTO> categoryDTOS;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }


    public List<ProductImagesDTO> getImagesDTOS() {
        return imagesDTOS;
    }

    public void setImagesDTOS(List<ProductImagesDTO> imagesDTOS) {
        this.imagesDTOS = imagesDTOS;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }



    public List<ProductOrderDTO> getProductOrderDTOS() {
        return productOrderDTOS;
    }

    public void setProductOrderDTOS(List<ProductOrderDTO> productOrderDTOS) {
        this.productOrderDTOS = productOrderDTOS;
    }
}
