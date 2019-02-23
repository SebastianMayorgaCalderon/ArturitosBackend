package com.brainstation.arturitos.domain;


import com.brainstation.arturitos.dtos.OrderDTO;
import com.brainstation.arturitos.dtos.ProductOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private int id;

    private List<ProductOrder> products;

    private String name;

    private String status;

    public Order(){}

    public Order(OrderDTO orderDTO){
        this.products = orderDTO.getProductOrderDTOS().stream().map(ProductOrder::new).collect(Collectors.toList());
        this.name = orderDTO.getName();
        this.status = orderDTO.getStatus();
        this.id = orderDTO.getId();
    }

    public List<ProductOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrder> products) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
