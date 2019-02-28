package com.brainstation.arturitos.utils;

public class MyExeption extends Exception{
    private String errorMsj;
    public MyExeption(String errorMsj){
        super(errorMsj);
        this.errorMsj =errorMsj;
    }
}
