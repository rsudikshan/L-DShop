package com.sr.L.DShop.payload.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponse {
    private Long productId;
    private String productName;
    private String productPrice;
    private String vendorName;
    private String categoryName;
    private String imageFileName;
}
