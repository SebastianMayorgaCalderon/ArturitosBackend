package com.brainstation.arturitos.dtos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Order")
@Table(name = "user_order")
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<ProductOrderDTO> productOrderDTOS;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserDTO user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name="type")
    private String type;

    @Column(name="status")
    private String status;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductOrderDTO> getProductOrderDTOS() {
        return productOrderDTOS;
    }

    public void setProductOrderDTOS(List<ProductOrderDTO> productOrderDTOS) {
        this.productOrderDTOS = productOrderDTOS;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
