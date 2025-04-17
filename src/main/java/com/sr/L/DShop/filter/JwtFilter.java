package com.sr.L.DShop.filter;

import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.service.concrete.JwtService;
import com.sr.L.DShop.service.concrete.LdUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final LdUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            String tokenUsername = jwtService.getUsername(token);
            if(tokenUsername == null){
                throw new UnauthorizedException("Invalid token");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenUsername);


            if( userDetails.getUsername()!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                if(!jwtService.isTokenExpired(token)){
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    ));
                }
                else {
                    throw new UnauthorizedException("Token is expired");
                }
            }
        }


        filterChain.doFilter(request,response);
    }
}
