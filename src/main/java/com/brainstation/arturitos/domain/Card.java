package com.brainstation.arturitos.domain;

import com.brainstation.arturitos.dtos.CardDTO;
import com.brainstation.arturitos.dtos.UserDTO;

public class Card {

    private int id;
    private String token;
    private String brand;
    private String lastDigits;

    public Card() {
    }
    public Card(CardDTO cardDTO){
        this.id = cardDTO.getId();
        this.token = cardDTO.getToken();
        this.brand = cardDTO.getBrand();
        this.lastDigits = cardDTO.getLastdigits();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }
}
