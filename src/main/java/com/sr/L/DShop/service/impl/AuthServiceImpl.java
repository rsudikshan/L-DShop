package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.LoginRequest;
import com.sr.L.DShop.payload.Request.RefreshRequest;
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
        throw new UnauthorizedException("Invalid credentials",loginRequest.getUsername());
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    Map<String,String> user = new HashMap<>();
    user.put("username",userDetails.getUsername());
    user.put("role",userDetails.getAuthorities().toString());

    Map<String,String> tokens = new HashMap<>();
    tokens.put("jwt",jwtService.generateJwt(loginRequest.getUsername()));
    tokens.put("refresh_token", jwtService.generateRefreshToken(loginRequest.getUsername()));

    Map<String,Object> responseMap = new HashMap<>();
    responseMap.put("user",user);
    responseMap.put("tokens",tokens);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return ResponseBuilder.success("login successful",responseMap);
}

    @Override
    public ResponseModel refresh(RefreshRequest refreshRequest) {
        String username = jwtService.getUsername(refreshRequest.getRefreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new UnauthorizedException("No such user");
        }

        if(jwtService.isTokenExpired(refreshRequest.getRefreshToken())){
            throw new UnauthorizedException("Refresh Token Expired, proceed to logging in again");
        }

        String newAccessToken = jwtService.generateJwt(username);
        return ResponseBuilder.success("New token",newAccessToken);
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


