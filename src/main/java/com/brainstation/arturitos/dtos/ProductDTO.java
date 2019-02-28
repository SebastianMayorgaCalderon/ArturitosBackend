package com.brainstation.arturitos.dtos;


import com.brainstation.arturitos.domain.Product;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal price;

    @Column(name="avaliable")
    private Boolean avaliable;

    @Column(name = "description")
    private String desription;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<ProductImagesDTO> imagesDTOS;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<CommentDTO> comments;

    @ManyToMany(mappedBy = "products")
    private List<CategoryDTO> categoryDTOS;

    @ManyToMany(mappedBy = "products")
    private List<OrderDTO> orderDTOS;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserDTO userDTO;

    public ProductDTO(){}

    public ProductDTO(Product product) {
        this.id= product.getId();
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    public Boolean getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
