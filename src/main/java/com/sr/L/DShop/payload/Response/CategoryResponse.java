package com.sr.L.DShop.payload.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String categoryName;
}
