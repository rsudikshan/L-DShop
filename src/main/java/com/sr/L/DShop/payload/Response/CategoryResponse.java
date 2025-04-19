package com.sr.L.DShop.payload.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
public class CategoryResponse {
    private Long id;
    private String categoryName;
}
