package com.sr.L.DShop.controllers;

import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.models.ResponseBody;
import com.sr.L.DShop.payload.Request.RegisterRequest;
import com.sr.L.DShop.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseBody> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok().body(authService.register(registerRequest, Roles.USER));
    }

}
