package com.sr.L.DShop.exceptions;


public class OrderException extends RuntimeException{

    public String message;

    OrderException(String message){
        super(message);
        this.message = message;
    }
}
