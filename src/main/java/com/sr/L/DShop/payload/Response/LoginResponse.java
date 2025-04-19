package com.sr.L.DShop.payload.Response;

import lombok.*;

@Builder
@Getter
@Setter
public class LoginResponse {
    private String role;
    private String username;
    private String jwt;
    private String refreshToken;
}
