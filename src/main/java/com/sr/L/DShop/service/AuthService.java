package com.sr.L.DShop.service;


import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.LoginRequest;
import com.sr.L.DShop.payload.Request.RefreshRequest;
import com.sr.L.DShop.payload.Request.RegisterRequest;

public interface AuthService {

    ResponseModel register(RegisterRequest registerRequest, Roles role);
    ResponseModel login(LoginRequest loginRequest);
    ResponseModel refresh(RefreshRequest refreshRequest);

}