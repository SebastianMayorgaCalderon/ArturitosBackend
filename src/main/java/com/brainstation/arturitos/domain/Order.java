package com.brainstation.arturitos.domain;


import com.brainstation.arturitos.dtos.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private int id;

    private List<Product> products;

    private String name;

    private String cardToken;

    public Order(){}

    public Order(OrderDTO orderDTO){
        this.products = orderDTO.getProducts().stream().map(Product::new).collect(Collectors.toList());
        this.name = orderDTO.getName();
        this.id = orderDTO.getId();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
}
