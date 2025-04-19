package com.sr.L.DShop.payload.Request;

import lombok.Getter;

@Getter
public class AddProductRequest {
    private Long categoryId;
    private String productName;
    private String productPrice;
}
