package com.sr.L.DShop.service;


import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.OrderRequest;

public interface OrderService {
    public ResponseModel setOrder(OrderRequest orderRequest);
}
