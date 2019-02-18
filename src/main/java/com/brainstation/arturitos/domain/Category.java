package com.brainstation.arturitos.domain;
import com.brainstation.arturitos.dtos.CategoryDTO;

public class Category {
    private int id;
    private String name;

    public Category(CategoryDTO categoryDTO){
        this.name = categoryDTO.getName();
        this.id = categoryDTO.getId();
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
}
