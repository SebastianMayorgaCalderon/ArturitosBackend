package com.brainstation.arturitos.Web;


public class MyResponce<N> {
    public N data;
    public String message;

    public MyResponce(N data, String message) {
        this.data = data;
        this.message = message;
    }
}
