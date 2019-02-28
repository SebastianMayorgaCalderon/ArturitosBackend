package com.brainstation.arturitos.dtos;


import com.brainstation.arturitos.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name="username",unique=true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email",unique=true)
    private String email;

    @Column(name = "user_profile_img_url")
    private String userProfileImgUrl;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<CommentDTO> comments;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<OrderDTO> orders;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<ProductDTO> Products;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<CardDTO> cards;



    public UserDTO(){}

    public List<ProductDTO> getProducts() {
        return Products;
    }

    public void setProducts(List<ProductDTO> products) {
        Products = products;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public UserDTO(User user){
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.userProfileImgUrl = user.getUserImgUrl();
        this.password= user.getPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserProfileImgUrl() {
        return userProfileImgUrl;
    }

    public void setUserProfileImgUrl(String userPRofileImgUrl) {
        this.userProfileImgUrl = userPRofileImgUrl;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
