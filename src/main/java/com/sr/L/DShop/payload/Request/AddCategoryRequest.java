package com.sr.L.DShop.payload.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddCategoryRequest {
    private String categoryName;
}
