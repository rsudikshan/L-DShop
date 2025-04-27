package com.sr.L.DShop.controllers;

import com.sr.L.DShop.annotations.OrderController;
import com.sr.L.DShop.payload.Request.OrderRequest;
import com.sr.L.DShop.service.OrderService;
import com.sr.L.DShop.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@OrderController
@RequiredArgsConstructor
public class OrderControllers {

    private final OrderServiceImpl orderService;

    @PostMapping("/setOrder")
    public ResponseEntity<?> setOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok().body(orderService.setOrder(orderRequest));
    }
}
