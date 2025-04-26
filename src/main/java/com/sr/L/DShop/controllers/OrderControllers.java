package com.sr.L.DShop.controllers;

import com.sr.L.DShop.annotations.OrderController;
import com.sr.L.DShop.payload.Request.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@OrderController
public class OrderControllers {

    @PostMapping("/setOrder")
    public ResponseEntity<?> setOrder(@RequestBody OrderRequest orderRequest){

        return null;
    }
}
