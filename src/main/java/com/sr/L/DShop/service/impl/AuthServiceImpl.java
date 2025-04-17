package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.LoginRequest;
import com.sr.L.DShop.payload.Request.RegisterRequest;
import com.sr.L.DShop.repo.UserRepo;
import com.sr.L.DShop.service.AuthService;
import com.sr.L.DShop.service.concrete.JwtService;
import com.sr.L.DShop.service.concrete.LdUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

private final UserRepo userRepo;
private final LdUserDetailsService userDetailsService;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;

@Override
public ResponseModel register(RegisterRequest registerRequest, Roles role){

    if(userRepo.existsByEmail(registerRequest.getEmail()) && !userRepo.existsByUsername(registerRequest.getUsername())){
        throw new UnauthorizedException("Email already exists",registerRequest.getEmail());
    }

    if(userRepo.existsByUsername(registerRequest.getUsername())){
        throw new UnauthorizedException("Username already exists",registerRequest.getUsername());
    }

    userRepo.save(registerDtoToEntityConverterHelper(registerRequest,role));
    return ResponseBuilder.success("success",registerRequest.getUsername());

}

@Override
public ResponseModel login(LoginRequest loginRequest) {
    Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),loginRequest.getPassword()
                    )
            );

    if(!authentication.isAuthenticated()){
        System.out.println("failed 2");
        throw new UnauthorizedException("Invalid credentials",loginRequest.getUsername());
    }

    Map<String,String> token = new HashMap<>();
    token.put("Bearer ",jwtService.generateJwt(loginRequest.getUsername()));
    token.put("Refresh token", jwtService.generateRefreshToken(loginRequest.getUsername()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return ResponseBuilder.success("login successful",token);
}

public LdUser registerDtoToEntityConverterHelper(RegisterRequest registerRequest, Roles role){

    return LdUser.builder()
                    .createdAt(LocalDateTime.now())
                    .email(registerRequest.getEmail())
                    .username(registerRequest.getUsername())
                    .password(new BCryptPasswordEncoder(12).encode(registerRequest.getPassword()))
                    .role(role)
                    .build();
}



}


