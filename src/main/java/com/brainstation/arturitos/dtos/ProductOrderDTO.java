package com.brainstation.arturitos.dtos;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProductOrder")
@Table(name="product_order")
public class ProductOrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private ProductDTO productDTO;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private OrderDTO orderDTO;

    @Column(name = "cuantity")
    private int cuantity;

    public ProductOrderDTO() {
    }

    public ProductOrderDTO(ProductDTO productDTO, OrderDTO orderDTO, int cuantity) {
        this.productDTO = productDTO;
        this.orderDTO = orderDTO;
        this.cuantity = cuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }
}
