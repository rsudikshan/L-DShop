package com.sr.L.DShop.exceptions.handler;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.exceptions.CategoryException;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(UnauthorizedException.class) basic response
//    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e){
//        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ResponseBuilder.failure(e.getMessage())
        );
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<?> handleCategoryException(CategoryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseBuilder.failure(e.getMessage())
        );
    }
}
