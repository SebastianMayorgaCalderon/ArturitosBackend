package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.ProductOrderDTO;

public class ProductOrder {

    private int productId;
    private int cant;

    public ProductOrder(){}

    public ProductOrder(ProductOrderDTO productOrderDTO){
        this.productId = productOrderDTO.getProductDTO().getId();
        this.cant = productOrderDTO.getCuantity();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
