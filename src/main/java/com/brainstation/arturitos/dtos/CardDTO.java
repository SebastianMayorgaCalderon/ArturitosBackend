package com.brainstation.arturitos.dtos;

import com.brainstation.arturitos.domain.Card;

import javax.persistence.*;

@Entity(name = "UserCard")
@Table(name = "user_card")
public class CardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "last_digits")
    private String lastdigits;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDTO userDTO;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.brand = card.getBrand();
        this.id = card.getId();
        this.lastdigits = card.getLastDigits();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLastdigits() {
        return lastdigits;
    }

    public void setLastdigits(String lastdigits) {
        this.lastdigits = lastdigits;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO productDTO) {
        this.userDTO = productDTO;
    }
}
