package com.sr.L.DShop.payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    public Long id;
    public Long categoryId;
    public String productName;
    public String productPrice;
}
