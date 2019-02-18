package com.brainstation.arturitos.dtos;

import com.brainstation.arturitos.domain.Category;

import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;


import javax.persistence.*;
import java.util.List;
@Entity(name = "Category")
@Table(name = "category")
public class CategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE})
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductDTO> products;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
    public CategoryDTO(){}

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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
