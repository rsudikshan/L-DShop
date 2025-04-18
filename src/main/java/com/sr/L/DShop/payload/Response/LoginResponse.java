package com.sr.L.DShop.payload.Response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String role;
    private String username;
}
