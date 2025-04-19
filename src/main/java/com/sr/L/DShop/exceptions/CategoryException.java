package com.sr.L.DShop.exceptions;

import com.sr.L.DShop.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryException extends RuntimeException{
    String message;
    Object object;

    public CategoryException(String message){
        super(message);
    }

}
