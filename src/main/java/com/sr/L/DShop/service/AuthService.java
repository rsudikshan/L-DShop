package com.sr.L.DShop.service;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.enums.Roles;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseBody;
import com.sr.L.DShop.payload.Request.RegisterRequest;
import com.sr.L.DShop.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;

    public ResponseBody register(RegisterRequest registerRequest, Roles role){

        if(userRepo.existsByEmail(registerRequest.getEmail()) && !userRepo.existsByUsername(registerRequest.getUsername())){
            throw new UnauthorizedException("Email already exists");
        }
        if(userRepo.existsByUsername(registerRequest.getUsername())){
            throw new UnauthorizedException("Username already exists");
        }

        userRepo.save(dtoToEntityConverterHelper(registerRequest,role));
        return ResponseBuilder.success("success",registerRequest.getUsername());

    }

    public LdUser dtoToEntityConverterHelper(RegisterRequest registerRequest,Roles role){

        return LdUser.builder()
                .createdAt(LocalDateTime.now())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(registerRequest.getPassword()))
                .role(role)
                .build();
    }


}
