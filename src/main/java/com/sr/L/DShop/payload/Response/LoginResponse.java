package com.sr.L.DShop.payload.Response;

import lombok.*;

@Builder
public class LoginResponse {
    private String role;
    private String username;
    private String jwt;
    private String refreshToken;
}
