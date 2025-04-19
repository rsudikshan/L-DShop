package com.sr.L.DShop.controllers;

import com.sr.L.DShop.annotations.AuthenticationController;
import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.LoginRequest;
import com.sr.L.DShop.payload.Request.RefreshRequest;
import com.sr.L.DShop.payload.Request.RegisterRequest;
import com.sr.L.DShop.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@AuthenticationController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthServiceImpl authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok().body(authService.register(registerRequest, Roles.USER));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseModel> refresh(@RequestBody RefreshRequest refreshRequest){
        return ResponseEntity.ok().body(authService.refresh(refreshRequest));
    }

    @PostMapping("/admin/auth/register")
    public ResponseEntity<?> adminRegister(@RequestBody RegisterRequest adminRegister){
        return ResponseEntity.ok().body(authService.register(adminRegister,Roles.ADMIN));
    }
    //need better response in admin just like login

    @PostMapping("/superAdmin/auth/register")
    public ResponseEntity<?> superAdminRegister(@RequestBody RegisterRequest adminRegister){
        return ResponseEntity.ok().body(authService.register(adminRegister,Roles.SUPER_ADMIN));
    }

}
