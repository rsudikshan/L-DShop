package com.sr.L.DShop.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
//use runtime exception instead of exception because its client side error and try catch is necessary otherwise
public class UnauthorizedException extends RuntimeException{

    String message;
    Object object;


    public UnauthorizedException(String message){
        super(message);
        this.message =  message;
    }
}
