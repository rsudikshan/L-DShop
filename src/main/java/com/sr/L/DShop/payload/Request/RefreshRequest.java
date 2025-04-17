package com.sr.L.DShop.payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {
    private String refreshToken;
}
