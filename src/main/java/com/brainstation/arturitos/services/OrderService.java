package com.brainstation.arturitos.services;

import com.brainstation.arturitos.repositories.OrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRespository orderRespository;
    @Autowired
    public OrderService(OrderRespository orderRespository){
        this.orderRespository = orderRespository;
    }
}
