package com.sr.L.DShop.exceptions;


//use runtime exception instead of exception because its client side error and try catch is necessary otherwise
public class UnauthorizedException extends RuntimeException{


    public UnauthorizedException(String message){
        super(message);
    }
}
